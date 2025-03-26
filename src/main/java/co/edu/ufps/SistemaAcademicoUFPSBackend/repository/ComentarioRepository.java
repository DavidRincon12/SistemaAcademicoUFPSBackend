package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Obtener comentarios por foro
    List<Comentario> findByForo(Foro foro);

    // Obtener comentarios por emisor
    List<Comentario> findByEmisor(Persona emisor);

    // Obtener comentarios más recientes de un foro
    @Query("SELECT c FROM Comentario c WHERE c.foro = :foro ORDER BY c.fechaCreacion DESC")
    List<Comentario> findRecentCommentsByForo(@Param("foro") Foro foro);

    // Buscar comentarios por contenido
    @Query("SELECT c FROM Comentario c WHERE LOWER(c.contenido) LIKE LOWER(CONCAT('%', :contenido, '%'))")
    List<Comentario> searchByContenido(@Param("contenido") String contenido);
}
