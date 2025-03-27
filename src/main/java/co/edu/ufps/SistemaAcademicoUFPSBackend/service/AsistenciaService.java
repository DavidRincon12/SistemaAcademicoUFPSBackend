package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsistenciaRepository;
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

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Asistencia> getAllAsistencias() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Asistencia> getAsistenciaById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Asistencia createAsistencia(Asistencia asistencia) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Asistencia updateAsistencia(Long id, Asistencia asistenciaDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteAsistencia(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Asistencia> findByEstudianteId(Long estudianteId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByClaseId(Long claseId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByFecha(Date fecha) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByEstudianteIdAndFechaBetween(Long estudianteId, Date fechaInicio, Date fechaFin) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Asistencia> findByEstudianteIdAndClaseIdAndFecha(Long estudianteId, Long claseId, Date fecha) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void registrarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public void consultarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}