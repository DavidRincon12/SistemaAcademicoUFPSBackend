package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {

    Comentario save(Comentario comentario);

    Comentario update(Comentario comentario);

    void delete(Long id);

    Optional<Comentario> findById(Long id);

    List<Comentario> findAll();

    List<Comentario> findByForo(Foro foro);

    List<Comentario> findByEmisor(Persona emisor);

    List<Comentario> findRecentCommentsByForo(Foro foro);

    List<Comentario> searchByContenido(String contenido);
}
