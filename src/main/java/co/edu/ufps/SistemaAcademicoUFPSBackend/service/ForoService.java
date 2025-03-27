package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ForoService {

    @Autowired
    private ForoRepository foroRepository;

    // Obtener todos los foros
    public List<Foro> getAllForos() {
        return foroRepository.findAll();
    }

    // Obtener un foro por ID
    public Optional<Foro> getForoById(Long id) {
        return foroRepository.findById(id);
    }

    // Crear un nuevo foro
    public Foro createForo(Foro foro) {
        return foroRepository.save(foro);
    }

    // Actualizar foro
    public Foro updateForo(Long id, Foro foroDetails) {
        return foroRepository.findById(id).map(foro -> {
            foro.setTema(foroDetails.getTema());
            foro.setDescripcion(foroDetails.getDescripcion());
            foro.setAutor(foroDetails.getAutor());
            foro.setFechaCreacion(foroDetails.getFechaCreacion());
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

    // Buscar foros por tema (sin importar mayúsculas/minúsculas)
    public List<Foro> getForosByTema(String tema) {
        return foroRepository.findByTemaIgnoreCase(tema);
    }

    // Buscar foros creados por un autor específico
    public List<Foro> getForosByAutor(Long autorId) {
        return foroRepository.findByAutorId(autorId);
    }

    // Obtener foros creados después de una fecha específica
    public List<Foro> getForosByFechaCreacionAfter(Date fecha) {
        return foroRepository.findByFechaCreacionAfter(fecha);
    }

    // Buscar foros por descripción parcial
    public List<Foro> searchForosByDescripcion(String descripcion) {
        return foroRepository.searchByDescripcion(descripcion);
    }

    // Contar cuántos foros ha creado un autor
    public long countForosByAutor(Long autorId) {
        return foroRepository.countForosByAutor(autorId);
    }
}
