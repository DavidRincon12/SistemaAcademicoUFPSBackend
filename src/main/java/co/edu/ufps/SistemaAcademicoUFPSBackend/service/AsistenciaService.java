package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsistenciaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ClaseRepository claseRepository;

    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id);
    }

    public Asistencia createAsistencia(Asistencia asistencia) {
        Estudiante estudiante = estudianteRepository.findById(asistencia.getEstudiante().getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Clase clase = claseRepository.findById(asistencia.getClase().getId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        asistencia.setEstudiante(estudiante);
        asistencia.setClase(clase);

        return asistenciaRepository.save(asistencia);
    }

    public Asistencia updateAsistencia(Long id, Asistencia asistenciaDetails) {
        return asistenciaRepository.findById(id).map(asistencia -> {
            Estudiante estudiante = estudianteRepository.findById(asistenciaDetails.getEstudiante().getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            Clase clase = claseRepository.findById(asistenciaDetails.getClase().getId())
                    .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

            asistencia.setEstudiante(estudiante);
            asistencia.setClase(clase);
            asistencia.setFecha(asistenciaDetails.getFecha());
            asistencia.setEstado(asistenciaDetails.getEstado());
            return asistenciaRepository.save(asistencia);
        }).orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
    }

    public void deleteAsistencia(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new RuntimeException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }

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

    // Métodos de negocio no implementados aún
    @Transactional
    public void registrarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public void consultarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
