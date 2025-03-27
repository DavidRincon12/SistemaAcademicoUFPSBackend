package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.CalendarioAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.CalendarioAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioAcademicoService {

    @Autowired
    private CalendarioAcademicoRepository calendarioAcademicoRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<CalendarioAcademico> getAllCalendarios() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<CalendarioAcademico> getCalendarioById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public CalendarioAcademico createCalendario(CalendarioAcademico calendario) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public CalendarioAcademico updateCalendario(Long id, CalendarioAcademico calendarioDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteCalendario(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<CalendarioAcademico> findByNombrePeriodo(String nombrePeriodo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<CalendarioAcademico> findByFechaDentroDelPeriodo(Date fecha) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<CalendarioAcademico> findByPeriodoEntreFechas(Date fechaInicio, Date fechaFin) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<CalendarioAcademico> findCalendarioActivo() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void agregarEvento() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public void listarFechasImportantes() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}