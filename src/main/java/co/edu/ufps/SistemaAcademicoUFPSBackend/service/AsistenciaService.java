package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoAsistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsistenciaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (asistencia.getEstado() == null) {
            asistencia.setEstado(EstadoAsistencia.AUSENTE); // Estado por defecto si no se especifica
        }

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

    public List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId) {
        return asistenciaRepository.findByEstudianteIdAndClaseId(estudianteId, claseId);
    }

    public List<Asistencia> findByEstado(EstadoAsistencia estado) {
        return asistenciaRepository.findByEstado(estado);
    }

    public List<Asistencia> findByClaseIdAndEstado(Long claseId, EstadoAsistencia estado) {
        return asistenciaRepository.findByClaseIdAndEstado(claseId, estado);
    }

    @Transactional
    public void registrarAsistencia(Long claseId, List<Asistencia> asistencias) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        for (Asistencia asistencia : asistencias) {
            Estudiante estudiante = estudianteRepository.findById(asistencia.getEstudiante().getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

            // Verificar si ya existe una asistencia para esta clase y estudiante
            List<Asistencia> existentes = asistenciaRepository.findByEstudianteIdAndClaseId(estudiante.getId(), clase.getId());
            if (!existentes.isEmpty()) {
                continue; // Ya hay asistencia registrada para este estudiante y clase
            }

            asistencia.setClase(clase);
            asistencia.setEstudiante(estudiante);
            if (asistencia.getEstado() == null) {
                asistencia.setEstado(EstadoAsistencia.AUSENTE); // Por defecto
            }
            asistenciaRepository.save(asistencia);
        }
    }


    @Transactional(readOnly = true)
    public void consultarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
