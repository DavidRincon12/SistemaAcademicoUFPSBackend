package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Notificacion;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Notificacion createNotificacion(Notificacion notificacion) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Notificacion updateNotificacion(Long id, Notificacion notificacionDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteNotificacion(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Notificacion> getNotificacionesByDestinatarioId(Long destinatarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Notificacion> getNotificacionesNoLeidas(Long destinatarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public long countNotificacionesNoLeidas(Long destinatarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void marcarComoLeido(Long notificacionId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void eliminarNotificacionLogica(Long notificacionId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}