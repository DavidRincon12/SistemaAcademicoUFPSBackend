package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    // Obtener todos los comentarios

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    // Obtener un comentario por ID
    public Optional<Comentario> getComentarioById(Long id) {
        return comentarioRepository.findById(id);
    }

    // Crear un nuevo comentario
    public Comentario createComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Actualizar un comentario
    public Comentario updateComentario(Long id, Comentario comentarioDetails) {
        return comentarioRepository.findById(id).map(comentario -> {
            comentario.setContenido(comentarioDetails.getContenido());
            comentario.setFechaCreacion(comentarioDetails.getFechaCreacion());
            comentario.setForo(comentarioDetails.getForo());
            comentario.setEmisor(comentarioDetails.getEmisor());
            return comentarioRepository.save(comentario);
        }).orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    // Eliminar un comentario
    public void deleteComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado");
        }
        comentarioRepository.deleteById(id);
    }

    // Métodos adicionales requeridos

    public List<Comentario> findByForo(Foro foro) {
        return comentarioRepository.findByForo(foro);
    }


    public List<Comentario> findByEmisor(Persona emisor) {
        return comentarioRepository.findByEmisor(emisor);
    }


    public List<Comentario> findRecentCommentsByForo(Foro foro) {
        return comentarioRepository.findRecentCommentsByForo(foro);
    }


    public List<Comentario> searchByContenido(String contenido) {
        return comentarioRepository.searchByContenido(contenido);

    }
    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void editarComentario(Long comentarioId, String nuevoContenido) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void eliminarComentario(Long comentarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}