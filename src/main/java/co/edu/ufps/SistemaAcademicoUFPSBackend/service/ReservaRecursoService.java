package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ReservaRecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaRecursoService {

    @Autowired
    private ReservaRecursoRepository reservaRecursoRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<ReservaRecurso> getAllReservas() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<ReservaRecurso> getReservaById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public ReservaRecurso createReserva(ReservaRecurso reserva) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public ReservaRecurso updateReserva(Long id, ReservaRecurso reservaDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteReserva(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<ReservaRecurso> getReservasByEstado(String estado) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<ReservaRecurso> getReservasActivas(Date fechaInicio, Date fechaFin) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<ReservaRecurso> getReservasByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public ReservaRecurso aprobarReserva(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public ReservaRecurso extenderReserva(Long id, Date nuevaFechaFin) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}