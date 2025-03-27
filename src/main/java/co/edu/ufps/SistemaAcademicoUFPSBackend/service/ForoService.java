package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ForoService {

    @Autowired
    private ForoRepository foroRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Foro> getAllForos() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Foro> getForoById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Foro createForo(Foro foro) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Foro updateForo(Long id, Foro foroDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteForo(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Foro> getForosByTema(String tema) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Foro> getForosByAutor(Long autorId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Foro> getForosByFechaCreacionAfter(Date fecha) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Foro> searchForosByDescripcion(String descripcion) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public long countForosByAutor(Long autorId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void agregarComentario(Long foroId, Comentario comentario) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void eliminarComentario(Long foroId, Long comentarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}