package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.AsignaturaEstudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaEstudianteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaEstudianteService {

    @Autowired
    private AsignaturaEstudianteRepository asignaturaEstudianteRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // Obtener todos los registros
    public List<AsignaturaEstudiante> getAllAsignaturasEstudiantes() {
        return asignaturaEstudianteRepository.findAll();
    }

    // Obtener por ID
    public Optional<AsignaturaEstudiante> getById(Long id) {
        return asignaturaEstudianteRepository.findById(id);
    }

    // Crear nuevo
    public AsignaturaEstudiante create(AsignaturaEstudiante ae) {
        if (ae.getEstudiante() != null && ae.getEstudiante().getId() != null) {
            Estudiante estudiante = estudianteRepository.findById(ae.getEstudiante().getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + ae.getEstudiante().getId()));
            ae.setEstudiante(estudiante);
        }

        if (ae.getAsignatura() != null && ae.getAsignatura().getId() != null) {
            Asignatura asignatura = asignaturaRepository.findById(ae.getAsignatura().getId())
                    .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con ID: " + ae.getAsignatura().getId()));
            ae.setAsignatura(asignatura);
        }

        return asignaturaEstudianteRepository.save(ae);
    }

    // Actualizar
    public AsignaturaEstudiante update(Long id, AsignaturaEstudiante detalles) {
        return asignaturaEstudianteRepository.findById(id).map(ae -> {
            ae.setPrimerPrevio(detalles.getPrimerPrevio());
            ae.setSegundoPrevio(detalles.getSegundoPrevio());
            ae.setTercerPrevio(detalles.getTercerPrevio());
            ae.setExamenFinal(detalles.getExamenFinal());
            ae.setDefinitiva(detalles.getDefinitiva());
            ae.setHabilitacion(detalles.isHabilitacion());
            ae.setVacacional(detalles.isVacacional());
            return asignaturaEstudianteRepository.save(ae);
        }).orElseThrow(() -> new RuntimeException("AsignaturaEstudiante no encontrada"));
    }

    // Eliminar por ID
    public void delete(Long id) {
        if (!asignaturaEstudianteRepository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado");
        }
        asignaturaEstudianteRepository.deleteById(id);
    }

    // Consultar por estudiante
    public List<AsignaturaEstudiante> getByEstudianteId(Long estudianteId) {
        return asignaturaEstudianteRepository.findByEstudianteId(estudianteId);
    }

    // Consultar por asignatura
    public List<AsignaturaEstudiante> getByAsignaturaId(Long asignaturaId) {
        return asignaturaEstudianteRepository.findByAsignaturaId(asignaturaId);
    }

    // ✅ Cancelar asignatura por estudiante y asignatura
    public String cancelarAsignatura(Long estudianteId, Long asignaturaId) {
        List<AsignaturaEstudiante> registros = asignaturaEstudianteRepository.findByEstudianteId(estudianteId)
                .stream()
                .filter(ae -> ae.getAsignatura().getId().equals(asignaturaId))
                .toList();

        if (registros.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una relación entre el estudiante y la asignatura.");
        }

        asignaturaEstudianteRepository.deleteAll(registros);
        return "Asignatura cancelada correctamente para el estudiante.";
    }
}
