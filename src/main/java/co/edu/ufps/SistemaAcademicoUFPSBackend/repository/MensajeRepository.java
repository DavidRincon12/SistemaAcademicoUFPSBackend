package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Obtener todos los mensajes de un chat ordenados por fecha de envío
    List<Mensaje> findByChatIdOrderByFechaEnvioAsc(Long chatId);

    // Obtener todos los mensajes enviados por un usuario
    List<Mensaje> findByEmisorId(Long emisorId);

    // Obtener todos los mensajes recibidos por un usuario
    List<Mensaje> findByDestinatarioId(Long destinatarioId);

    // Obtener mensajes entre dos usuarios en un chat específico
    @Query("SELECT m FROM Mensaje m WHERE m.chat.id = :chatId AND ((m.emisor.id = :usuario1 AND m.destinatario.id = :usuario2) OR (m.emisor.id = :usuario2 AND m.destinatario.id = :usuario1)) ORDER BY m.fechaEnvio ASC")
    List<Mensaje> findMensajesEntreUsuariosEnChat(@Param("chatId") Long chatId, @Param("usuario1") Long usuario1, @Param("usuario2") Long usuario2);

    // Obtener los mensajes no leídos de un usuario en un chat
    @Query("SELECT m FROM Mensaje m WHERE m.chat.id = :chatId AND m.destinatario.id = :destinatarioId AND m.estado = 'NO_LEÍDO'")
    List<Mensaje> findMensajesNoLeidos(@Param("chatId") Long chatId, @Param("destinatarioId") Long destinatarioId);

    // Contar cuántos mensajes no leídos tiene un usuario en un chat
    @Query("SELECT COUNT(m) FROM Mensaje m WHERE m.chat.id = :chatId AND m.destinatario.id = :destinatarioId AND m.estado = 'NO_LEÍDO'")
    long countMensajesNoLeidos(@Param("chatId") Long chatId, @Param("destinatarioId") Long destinatarioId);
}
