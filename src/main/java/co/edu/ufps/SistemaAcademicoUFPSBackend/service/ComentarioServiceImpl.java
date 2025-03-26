package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario update(Comentario comentario) {
        // Aquí puedes agregar lógica adicional o validaciones antes de actualizar
        return comentarioRepository.save(comentario);
    }

    @Override
    public void delete(Long id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public List<Comentario> findByForo(Foro foro) {
        return comentarioRepository.findByForo(foro);
    }

    @Override
    public List<Comentario> findByEmisor(Persona emisor) {
        return comentarioRepository.findByEmisor(emisor);
    }

    @Override
    public List<Comentario> findRecentCommentsByForo(Foro foro) {
        return comentarioRepository.findRecentCommentsByForo(foro);
    }

    @Override
    public List<Comentario> searchByContenido(String contenido) {
        return comentarioRepository.searchByContenido(contenido);
    }
}
