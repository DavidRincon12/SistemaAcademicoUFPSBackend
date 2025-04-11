package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ComentarioRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ForoRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ForoRepository foroRepository;

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> getComentarioById(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario createComentario(Comentario comentario) {
        Long emisorId = comentario.getEmisor().getId();
        Long foroId = comentario.getForo().getId();

        Persona emisor = personaRepository.findById(emisorId)
                .orElseThrow(() -> new RuntimeException("Emisor no encontrado con ID: " + emisorId));

        Foro foro = foroRepository.findById(foroId)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado con ID: " + foroId));

        comentario.setEmisor(emisor);
        comentario.setForo(foro);

        return comentarioRepository.save(comentario);
    }

    public Comentario updateComentario(Long id, Comentario comentarioDetails) {
        return comentarioRepository.findById(id).map(comentario -> {
            comentario.setContenido(comentarioDetails.getContenido());
            comentario.setFechaCreacion(comentarioDetails.getFechaCreacion());

            Persona emisor = personaRepository.findById(comentarioDetails.getEmisor().getId())
                    .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));
            Foro foro = foroRepository.findById(comentarioDetails.getForo().getId())
                    .orElseThrow(() -> new RuntimeException("Foro no encontrado"));

            comentario.setEmisor(emisor);
            comentario.setForo(foro);

            return comentarioRepository.save(comentario);
        }).orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    public void deleteComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado");
        }
        comentarioRepository.deleteById(id);
    }

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
