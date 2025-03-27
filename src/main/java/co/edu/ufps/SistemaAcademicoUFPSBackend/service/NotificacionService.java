package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Notificacion;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    // Obtener todas las notificaciones
    public List<Notificacion> getAllNotificaciones() {
        return notificacionRepository.findAll();
    }

    // Obtener una notificación por ID
    public Optional<Notificacion> getNotificacionById(Long id) {
        return notificacionRepository.findById(id);
    }

    // Obtener todas las notificaciones de un destinatario, ordenadas por fecha de envío
    public List<Notificacion> getNotificacionesByDestinatarioId(Long destinatarioId) {
        return notificacionRepository.findByDestinatarioIdOrderByFechaEnvioDesc(destinatarioId);
    }

    // Obtener notificaciones no leídas de un destinatario
    public List<Notificacion> getNotificacionesNoLeidas(Long destinatarioId) {
        return notificacionRepository.findNotificacionesNoLeidas(destinatarioId);
    }

    // Contar notificaciones no leídas de un destinatario
    public long countNotificacionesNoLeidas(Long destinatarioId) {
        return notificacionRepository.countNotificacionesNoLeidas(destinatarioId);
    }

    // Crear una nueva notificación
    public Notificacion createNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    // Actualizar una notificación
    public Notificacion updateNotificacion(Long id, Notificacion notificacionDetails) {
        return notificacionRepository.findById(id).map(notificacion -> {
            notificacion.setContenido(notificacionDetails.getContenido());
            notificacion.setFechaEnvio(notificacionDetails.getFechaEnvio());
            return notificacionRepository.save(notificacion);
        }).orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    // Eliminar una notificación
    public void deleteNotificacion(Long id) {
        if (!notificacionRepository.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada");
        }
        notificacionRepository.deleteById(id);
    }
}
