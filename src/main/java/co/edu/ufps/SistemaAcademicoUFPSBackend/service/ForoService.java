package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ComentarioRepository;
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
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private PersonaService personaService;

    // Obtener todos los foros
    public List<Foro> getAllForos() {
        return foroRepository.findAll();
    }

    // Obtener un foro por ID
    public Optional<Foro> getForoById(Long id) {
        return foroRepository.findById(id);
    }

    // Crear un nuevo foro (con autor gestionado)
    public Foro createForo(Foro foro) {
        Long autorId = foro.getAutor().getId();

        // Buscar y asociar el autor como entidad gestionada
        Persona autor = personaService.getPersonById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + autorId));

        foro.setAutor(autor);

        return foroRepository.save(foro);
    }

    // Actualizar foro
    public Foro updateForo(Long id, Foro foroDetails) {
        return foroRepository.findById(id).map(foro -> {
            foro.setTema(foroDetails.getTema());
            foro.setDescripcion(foroDetails.getDescripcion());
            foro.setFechaCreacion(foroDetails.getFechaCreacion());

            // Asociar el autor correctamente
            Long autorId = foroDetails.getAutor().getId();
            Persona autor = personaService.getPersonById(autorId)
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + autorId));
            foro.setAutor(autor);

            return foroRepository.save(foro);
        }).orElseThrow(() -> new RuntimeException("Foro no encontrado"));
    }

    // Eliminar foro
    public void deleteForo(Long id) {
        if (!foroRepository.existsById(id)) {
            throw new RuntimeException("Foro no encontrado");
        }
        foroRepository.deleteById(id);
    }

    public List<Foro> getForosByTema(String tema) {
        return foroRepository.findByTemaIgnoreCase(tema);
    }

    public List<Foro> getForosByAutor(Long autorId) {
        return foroRepository.findByAutorId(autorId);
    }

    public List<Foro> getForosByFechaCreacionAfter(Date fecha) {
        return foroRepository.findByFechaCreacionAfter(fecha);
    }

    public List<Foro> searchForosByDescripcion(String descripcion) {
        return foroRepository.searchByDescripcion(descripcion);
    }

    public long countForosByAutor(Long autorId) {
        return foroRepository.countForosByAutor(autorId);
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void agregarComentario(Long foroId, Comentario comentario) {
        Foro foro = foroRepository.findById(foroId)
                .orElseThrow(() -> new RuntimeException("Foro no encontrado con ID: " + foroId));

        Long emisorId = comentario.getEmisor().getId();
        Persona emisor = personaService.getPersonById(emisorId)
                .orElseThrow(() -> new RuntimeException("Emisor no encontrado con ID: " + emisorId));

        comentario.setForo(foro);
        comentario.setEmisor(emisor);
        comentario.setFechaCreacion(new Date());

        comentarioRepository.save(comentario);
    }


    @Transactional
    public void eliminarComentario(Long foroId, Long comentarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
