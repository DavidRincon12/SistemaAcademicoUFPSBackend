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

    public List<ReservaRecurso> getAllReservas() {
        return reservaRecursoRepository.findAll();
    }

    public Optional<ReservaRecurso> getReservaById(Long id) {
        return reservaRecursoRepository.findById(id);
    }

    public List<ReservaRecurso> getReservasByEstado(String estado) {
        return reservaRecursoRepository.findByEstado(estado);
    }

    public List<ReservaRecurso> getReservasActivas(Date inicio, Date fin) {
        return reservaRecursoRepository.findReservasSolapadas(inicio, fin);
    }


    public List<ReservaRecurso> getReservasByNombre(String nombre) {
        return reservaRecursoRepository.findByNombre(nombre);
    }

    @Transactional
    public ReservaRecurso createReserva(ReservaRecurso reserva) {
        // Validar conflictos de horario
        List<ReservaRecurso> existentes = reservaRecursoRepository
                .findByRecursoAcademicoIdAndEstadoIn(reserva.getRecursoAcademico().getId(), List.of("Pendiente", "Aprobada"));

        for (ReservaRecurso r : existentes) {
            if (r.getFechaInicio().before(reserva.getFechaFin()) && r.getFechaFin().after(reserva.getFechaInicio())) {
                throw new RuntimeException("Conflicto de horario con reserva existente ID: " + r.getId());
            }
        }
        reserva.setEstado("Pendiente");
        return reservaRecursoRepository.save(reserva);
    }

    @Transactional
    public ReservaRecurso updateReserva(Long id, ReservaRecurso reservaDetails) {
        return reservaRecursoRepository.findById(id).map(reserva -> {
            reserva.setFechaInicio(reservaDetails.getFechaInicio());
            reserva.setFechaFin(reservaDetails.getFechaFin());
            reserva.setEstado(reservaDetails.getEstado());
            reserva.setNombre(reservaDetails.getNombre());
            return reservaRecursoRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Transactional
    public ReservaRecurso aprobarReserva(Long id) {
        return reservaRecursoRepository.findById(id).map(reserva -> {
            reserva.setEstado("Aprobada");
            return reservaRecursoRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Transactional
    public void deleteReserva(Long id) {
        if (!reservaRecursoRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada");
        }
        reservaRecursoRepository.deleteById(id);
    }

    @Transactional
    public ReservaRecurso extenderReserva(Long id, Date nuevaFechaFin) {
        ReservaRecurso reserva = reservaRecursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // Validar solapamientos
        List<ReservaRecurso> existentes = reservaRecursoRepository
                .findByRecursoAcademicoIdAndEstadoIn(reserva.getRecursoAcademico().getId(), List.of("Pendiente", "Aprobada"));
        for (ReservaRecurso r : existentes) {
            if (!r.getId().equals(id)
                    && r.getFechaInicio().before(nuevaFechaFin)
                    && r.getFechaFin().after(reserva.getFechaInicio())) {
                throw new RuntimeException("Extensión genera conflicto con reserva ID: " + r.getId());
            }
        }
        reserva.setFechaFin(nuevaFechaFin);
        return reservaRecursoRepository.save(reserva);
    }
}