package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    // Buscar un chat entre dos personas
    @Query("SELECT c FROM Chat c WHERE (c.participante1 = ?1 AND c.participante2 = ?2) OR (c.participante1 = ?2 AND c.participante2 = ?1)")
    Optional<Chat> findByParticipantes(Persona participante1, Persona participante2);

    // Listar los chats en los que una persona está involucrada
    @Query("SELECT c FROM Chat c WHERE c.participante1 = ?1 OR c.participante2 = ?1")
    List<Chat> findByPersona(Persona persona);

    // Buscar chats creados después de una fecha específica
    @Query("SELECT c FROM Chat c WHERE c.fechaCreacion > ?1")
    List<Chat> findByFechaCreacionAfter(java.util.Date fecha);
}
