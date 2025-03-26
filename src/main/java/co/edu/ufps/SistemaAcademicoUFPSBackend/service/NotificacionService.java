package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Notificacion;
import java.util.List;
import java.util.Optional;

public interface NotificacionService {

    Notificacion save(Notificacion notificacion);

    Notificacion update(Notificacion notificacion);

    void delete(Long id);

    Optional<Notificacion> findById(Long id);

    List<Notificacion> findAll();

    List<Notificacion> findByDestinatarioIdOrderByFechaEnvioDesc(Long destinatarioId);

    List<Notificacion> findNotificacionesNoLeidas(Long destinatarioId);

    long countNotificacionesNoLeidas(Long destinatarioId);
}
