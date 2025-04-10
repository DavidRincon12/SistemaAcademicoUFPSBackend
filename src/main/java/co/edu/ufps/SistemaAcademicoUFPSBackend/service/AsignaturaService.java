package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        // Asociar docente
        Docente docente = docenteRepository.findById(asignatura.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        asignatura.setDocente(docente);

        // Asociar materia
        Materia materia = materiaRepository.findById(asignatura.getMateria().getId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        asignatura.setMateria(materia);

        // Asociar estudiantes gestionados
        if (asignatura.getEstudiantes() != null && !asignatura.getEstudiantes().isEmpty()) {
            List<Estudiante> estudiantesGestionados = asignatura.getEstudiantes().stream()
                    .map(est -> estudianteRepository.findById(est.getId())
                            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + est.getId())))
                    .collect(Collectors.toList());
            asignatura.setEstudiantes(estudiantesGestionados);
        }

        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Long id, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(id).map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());

            Docente docente = docenteRepository.findById(asignaturaDetails.getDocente().getId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
            asignatura.setDocente(docente);

            Materia materia = materiaRepository.findById(asignaturaDetails.getMateria().getId())
                    .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
            asignatura.setMateria(materia);

            List<Estudiante> estudiantesGestionados = asignaturaDetails.getEstudiantes().stream()
                    .map(est -> estudianteRepository.findById(est.getId())
                            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + est.getId())))
                    .collect(Collectors.toList());
            asignatura.setEstudiantes(estudiantesGestionados);

            asignatura.setPrimerPrevio(asignaturaDetails.getPrimerPrevio());
            asignatura.setSegundoPrevio(asignaturaDetails.getSegundoPrevio());
            asignatura.setTercerPrevio(asignaturaDetails.getTercerPrevio());
            asignatura.setExamenFinal(asignaturaDetails.getExamenFinal());
            asignatura.setDefinitiva(asignaturaDetails.getDefinitiva());
            asignatura.setHabilitacion(asignaturaDetails.isHabilitacion());
            asignatura.setVacacional(asignaturaDetails.isVacacional());

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

    public List<Asignatura> findByEstudianteId(Long estudianteId) {
        return asignaturaRepository.findByEstudianteId(estudianteId);
    }

    public List<Asignatura> findByHabilitacion() {
        return asignaturaRepository.findByHabilitacionTrue();
    }

    public List<Asignatura> findByVacacional() {
        return asignaturaRepository.findByVacacionalTrue();
    }

    public boolean calcularDefinitiva(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    public boolean verificarAprobacion(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
