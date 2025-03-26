package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Obtener todas las notificaciones de un destinatario, ordenadas por fecha de envío
    List<Notificacion> findByDestinatarioIdOrderByFechaEnvioDesc(Long destinatarioId);

    // Obtener notificaciones no leídas de un destinatario
    @Query("SELECT n FROM Notificacion n WHERE n.destinatario.id = :destinatarioId AND n.contenido NOT LIKE '%[LEÍDO]%'")
    List<Notificacion> findNotificacionesNoLeidas(@Param("destinatarioId") Long destinatarioId);

    // Contar las notificaciones no leídas de un destinatario
    @Query("SELECT COUNT(n) FROM Notificacion n WHERE n.destinatario.id = :destinatarioId AND n.contenido NOT LIKE '%[LEÍDO]%'")
    long countNotificacionesNoLeidas(@Param("destinatarioId") Long destinatarioId);
}
