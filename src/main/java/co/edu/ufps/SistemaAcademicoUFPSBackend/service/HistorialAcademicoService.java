package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialAcademicoService {

    @Autowired
    private HistorialAcademicoRepository historialAcademicoRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<HistorialAcademico> getAllHistoriales() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<HistorialAcademico> getHistorialById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public HistorialAcademico createHistorial(HistorialAcademico historial) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public HistorialAcademico updateHistorial(Long id, HistorialAcademico historialDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteHistorial(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<HistorialAcademico> getHistorialByEstudianteId(Long estudianteId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Float getPromedioPonderadoByEstudianteId(Long estudianteId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public int countMateriasAprobadasByEstudianteId(Long estudianteId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public int countMateriasProcesoByEstudianteId(Long estudianteId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void calcularPonderado(Long historialId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}