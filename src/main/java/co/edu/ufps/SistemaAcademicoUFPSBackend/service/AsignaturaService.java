package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Horario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        // 1. Asociar docente
        Docente docente = docenteRepository.findById(asignatura.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    
        // 2. Verificar disponibilidad del docente para cada horario de la asignatura
        if (asignatura.getHorarios() != null) {
            for (Horario horario : asignatura.getHorarios()) {
                verificarDisponibilidadDocente(docente, horario);
            }
        }
    
        // 3. Verificar carga horaria del docente
        verificarCargaHorariaDocente(docente, asignatura.getHorasSemanales());
    
        // 4. Asociar materia
        Materia materia = materiaRepository.findById(asignatura.getMateria().getId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        asignatura.setMateria(materia);
    
        // 5. Asignar docente a la asignatura
        asignatura.setDocente(docente);
    
        // 6. Actualizar carga horaria del docente
        short nuevaCargaHoraria = (short) (docente.getCargaHoraria() + asignatura.getHorasSemanales());
        docente.setCargaHoraria(nuevaCargaHoraria);
        docenteRepository.save(docente);
    
        // 7. Guardar la asignatura
        return asignaturaRepository.save(asignatura);
    }
    
    

    public Asignatura updateAsignatura(Long id, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(id).map(asignatura -> {
            // Actualizar el nombre de la asignatura
            asignatura.setNombre(asignaturaDetails.getNombre());
    
            // Validar y asociar el docente
            Docente docente = docenteRepository.findById(asignaturaDetails.getDocente().getId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    
            // Verificar disponibilidad del docente para los horarios de la asignatura
            if (asignaturaDetails.getHorarios() != null) {
                for (Horario horario : asignaturaDetails.getHorarios()) {
                    verificarDisponibilidadDocente(docente, horario);
                }
            }
    
            // Verificar carga horaria del docente
            int horasSemanales = asignaturaDetails.getHorasSemanales();
            verificarCargaHorariaDocente(docente, horasSemanales);
    
            // Asociar el docente a la asignatura
            asignatura.setDocente(docente);
    
            // Validar y asociar la materia
            Materia materia = materiaRepository.findById(asignaturaDetails.getMateria().getId())
                    .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
            asignatura.setMateria(materia);
    
            // Actualizar los horarios de la asignatura
            asignatura.getHorarios().clear();
            asignatura.getHorarios().addAll(asignaturaDetails.getHorarios());
    
            // Guardar la asignatura actualizada
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public void deleteAsignatura(Long id) {
        if (!asignaturaRepository.existsById(id)) {
            throw new RuntimeException("Asignatura no encontrada");
        }
        asignaturaRepository.deleteById(id);
    }

    public Optional<Asignatura> findByNombre(String nombre) {
        return asignaturaRepository.findByNombre(nombre);
    }

    public List<Asignatura> findByDocenteId(Long docenteId) {
        return asignaturaRepository.findByDocenteId(docenteId);
    }

    public List<Asignatura> findByMateriaId(Long materiaId) {
        return asignaturaRepository.findByMateriaId(materiaId);
    }

    public boolean calcularDefinitiva(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    public boolean verificarAprobacion(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

@Autowired
private HorarioRepository horarioRepository;

private void verificarDisponibilidadDocente(Docente docente, Horario horarioMateria) {
    List<Horario> horariosDelDocente = horarioRepository.findByDocenteId(docente.getId());

    for (Horario horarioDocente : horariosDelDocente) {
        if (horarioDocente.getDia().equals(horarioMateria.getDia())) {
            if ((horarioMateria.getHoraInicio().isBefore(horarioDocente.getHoraFin()) &&
                horarioMateria.getHoraFin().isAfter(horarioDocente.getHoraInicio())) ||
                (horarioMateria.getHoraInicio().equals(horarioDocente.getHoraInicio()) &&
                 horarioMateria.getHoraFin().equals(horarioDocente.getHoraFin()))) {
                throw new RuntimeException("El docente no tiene disponibilidad para esta asignatura debido a conflictos de horario.");
            }
        }
    }
}

    
    

    private void verificarCargaHorariaDocente(Docente docente, int horasMateria) {
        // Verificar si el docente excede su carga horaria máxima
        if (docente.getCargaHoraria() + horasMateria > docente.getCargaHorariaMaxima()) {
            throw new RuntimeException("El docente excede su carga horaria máxima.");
        }
    }
    
}
