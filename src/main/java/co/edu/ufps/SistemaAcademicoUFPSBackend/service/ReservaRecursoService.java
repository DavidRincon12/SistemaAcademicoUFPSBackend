package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ReservaRecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaRecursoService {

    @Autowired
    private ReservaRecursoRepository reservaRecursoRepository;

    // Obtener todas las reservas
    public List<ReservaRecurso> getAllReservas() {
        return reservaRecursoRepository.findAll();
    }

    // Obtener una reserva por ID
    public Optional<ReservaRecurso> getReservaById(Long id) {
        return reservaRecursoRepository.findById(id);
    }

    // Obtener reservas por estado
    public List<ReservaRecurso> getReservasByEstado(String estado) {
        return reservaRecursoRepository.findByEstado(estado);
    }

    // Obtener reservas activas dentro de un rango de fechas
    public List<ReservaRecurso> getReservasActivas(Date fechaInicio, Date fechaFin) {
        return reservaRecursoRepository.findByFechaInicioBeforeAndFechaFinAfter(fechaInicio, fechaFin);
    }

    // Obtener reservas por nombre del recurso
    public List<ReservaRecurso> getReservasByNombre(String nombre) {
        return reservaRecursoRepository.findByNombre(nombre);
    }

    // Crear una nueva reserva
    public ReservaRecurso createReserva(ReservaRecurso reserva) {
        return reservaRecursoRepository.save(reserva);
    }

    // Actualizar una reserva
    public ReservaRecurso updateReserva(Long id, ReservaRecurso reservaDetails) {
        return reservaRecursoRepository.findById(id).map(reserva -> {
            reserva.setFechaInicio(reservaDetails.getFechaInicio());
            reserva.setFechaFin(reservaDetails.getFechaFin());
            reserva.setEstado(reservaDetails.getEstado());
            reserva.setNombre(reservaDetails.getNombre());
            return reservaRecursoRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    // Aprobar una reserva
    public ReservaRecurso aprobarReserva(Long id) {
        return reservaRecursoRepository.findById(id).map(reserva -> {
            reserva.setEstado("Aprobada");
            return reservaRecursoRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    // Eliminar una reserva
    public void deleteReserva(Long id) {
        if (!reservaRecursoRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada");
        }
        reservaRecursoRepository.deleteById(id);
    }
}
