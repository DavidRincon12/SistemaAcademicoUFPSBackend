package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Notificacion;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion update(Notificacion notificacion) {
        // Se pueden agregar validaciones o lógica adicional antes de actualizar
        return notificacionRepository.save(notificacion);
    }

    @Override
    public void delete(Long id) {
        notificacionRepository.deleteById(id);
    }

    @Override
    public Optional<Notificacion> findById(Long id) {
        return notificacionRepository.findById(id);
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public List<Notificacion> findByDestinatarioIdOrderByFechaEnvioDesc(Long destinatarioId) {
        return notificacionRepository.findByDestinatarioIdOrderByFechaEnvioDesc(destinatarioId);
    }

    @Override
    public List<Notificacion> findNotificacionesNoLeidas(Long destinatarioId) {
        return notificacionRepository.findNotificacionesNoLeidas(destinatarioId);
    }

    @Override
    public long countNotificacionesNoLeidas(Long destinatarioId) {
        return notificacionRepository.countNotificacionesNoLeidas(destinatarioId);
    }
}
