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

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Comentario> getAllComentarios() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Comentario> getComentarioById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Comentario createComentario(Comentario comentario) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Comentario updateComentario(Long id, Comentario comentarioDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteComentario(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Comentario> findByForo(Foro foro) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Comentario> findByEmisor(Persona emisor) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Comentario> findRecentCommentsByForo(Foro foro) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Comentario> searchByContenido(String contenido) {
        throw new UnsupportedOperationException("Método no implementado");
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