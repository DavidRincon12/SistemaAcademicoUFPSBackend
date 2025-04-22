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

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        Docente docente = docenteRepository.findById(asignatura.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        Materia materia = materiaRepository.findById(asignatura.getMateria().getId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        int horasSemanales = materia.getHorasSemanales();

        if (asignatura.getHorarios() != null) {
            for (Horario horario : asignatura.getHorarios()) {
                verificarDisponibilidadDocente(docente, horario);
            }
        }

        verificarCargaHorariaDocente(docente, horasSemanales);

        asignatura.setDocente(docente);
        asignatura.setMateria(materia);

        docente.setCargaHoraria((short) (docente.getCargaHoraria() + horasSemanales));
        docenteRepository.save(docente);

        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Long id, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(id).map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());

            Docente docente = docenteRepository.findById(asignaturaDetails.getDocente().getId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

            Materia materia = materiaRepository.findById(asignaturaDetails.getMateria().getId())
                    .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

            int horasSemanales = materia.getHorasSemanales();

            if (asignaturaDetails.getHorarios() != null) {
                for (Horario horario : asignaturaDetails.getHorarios()) {
                    verificarDisponibilidadDocente(docente, horario);
                }
            }

            verificarCargaHorariaDocente(docente, horasSemanales);

            asignatura.setDocente(docente);
            asignatura.setMateria(materia);

            asignatura.getHorarios().clear();
            asignatura.getHorarios().addAll(asignaturaDetails.getHorarios());
            asignatura.getDocente().setCargaHoraria((short) (asignatura.getDocente().getCargaHoraria() + asignatura.getMateria().getHorasSemanales()));
            docenteRepository.save(asignatura.getDocente());

            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public Asignatura cambiarDocente(Long asignaturaId, Long nuevoDocenteId) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    
        // Si hay un docente actual, ajustar su carga horaria
        Docente docenteActual = asignatura.getDocente();
        if (docenteActual != null) {
            docenteActual.setCargaHoraria((short) (docenteActual.getCargaHoraria() - asignatura.getMateria().getHorasSemanales()));
            docenteRepository.save(docenteActual);
        }
    
        if (nuevoDocenteId != null) {
            Docente nuevoDocente = docenteRepository.findById(nuevoDocenteId)
                    .orElseThrow(() -> new RuntimeException("Nuevo docente no encontrado"));
    
            // Verificar disponibilidad para los horarios
            for (Horario horario : asignatura.getHorarios()) {
                verificarDisponibilidadDocente(nuevoDocente, horario);
            }
    
            // Verificar carga horaria
            verificarCargaHorariaDocente(nuevoDocente, asignatura.getMateria().getHorasSemanales());

            // Asignar el nuevo docente y actualizar su carga horaria
            nuevoDocente.setCargaHoraria((short) (nuevoDocente.getCargaHoraria() + asignatura.getMateria().getHorasSemanales()));
            docenteRepository.save(nuevoDocente);
            asignatura.setDocente(nuevoDocente);
        } else {
            // Si el nuevo docente es null, se elimina el docente de la asignatura
            asignatura.setDocente(null);
        }
    
        return asignaturaRepository.save(asignatura);
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
        if (docente.getCargaHoraria() + horasMateria > docente.getCargaHorariaMaxima()) {
            throw new RuntimeException("El docente excede su carga horaria máxima.");
        }
    }
}
