package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    // Obtener todas las asistencias
    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    // Obtener una asistencia por ID
    public Optional<Asistencia> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id);
    }

    // Crear una nueva asistencia
    public Asistencia createAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    // Actualizar una asistencia
    public Asistencia updateAsistencia(Long id, Asistencia asistenciaDetails) {
        return asistenciaRepository.findById(id).map(asistencia -> {
            asistencia.setEstudiante(asistenciaDetails.getEstudiante());
            asistencia.setClase(asistenciaDetails.getClase());
            asistencia.setFecha(asistenciaDetails.getFecha());
            asistencia.setEstado(asistenciaDetails.getEstado());
            return asistenciaRepository.save(asistencia);
        }).orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
    }

    // Eliminar una asistencia
    public void deleteAsistencia(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new RuntimeException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }

    // Métodos adicionales requeridos

    public List<Asistencia> findByEstudianteId(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId);
    }

    public List<Asistencia> findByClaseId(Long claseId) {
        return asistenciaRepository.findByClaseId(claseId);
    }

    public List<Asistencia> findByFecha(Date fecha) {
        return asistenciaRepository.findByFecha(fecha);
    }

    public List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId) {
        return asistenciaRepository.findByEstudianteIdAndClaseId(estudianteId, claseId);
    }

    public List<Asistencia> findByEstudianteIdAndFechaBetween(Long estudianteId, Date fechaInicio, Date fechaFin) {
        return asistenciaRepository.findByEstudianteIdAndFechaBetween(estudianteId, fechaInicio, fechaFin);
    }

    public Optional<Asistencia> findByEstudianteIdAndClaseIdAndFecha(Long estudianteId, Long claseId, Date fecha) {
        return asistenciaRepository.findByEstudianteIdAndClaseIdAndFecha(estudianteId, claseId, fecha);
    }
}
