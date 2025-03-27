package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RecursoAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoAcademicoService {

    @Autowired
    private RecursoAcademicoRepository recursoAcademicoRepository;

    // Obtener todos los recursos académicos
    public List<RecursoAcademico> getAllRecursos() {
        return recursoAcademicoRepository.findAll();
    }

    // Obtener un recurso académico por ID
    public Optional<RecursoAcademico> getRecursoById(Long id) {
        return recursoAcademicoRepository.findById(id);
    }

    // Buscar recursos por nombre
    public List<RecursoAcademico> getRecursosByNombre(String nombre) {
        return recursoAcademicoRepository.findByNombre(nombre);
    }

    // Buscar recursos por ubicación
    public List<RecursoAcademico> getRecursosByUbicacion(String ubicacion) {
        return recursoAcademicoRepository.findByUbicacionRecurso(ubicacion);
    }

    // Buscar recursos por persona responsable
    public List<RecursoAcademico> getRecursosByPersonaId(Long personaId) {
        return recursoAcademicoRepository.findByPersonaId(personaId);
    }

    // Crear un nuevo recurso académico
    public RecursoAcademico createRecurso(RecursoAcademico recurso) {
        return recursoAcademicoRepository.save(recurso);
    }

    // Actualizar un recurso académico
    public RecursoAcademico updateRecurso(Long id, RecursoAcademico recursoDetails) {
        return recursoAcademicoRepository.findById(id).map(recurso -> {
            recurso.setNombre(recursoDetails.getNombre());
            recurso.setUbicacionRecurso(recursoDetails.getUbicacionRecurso());
            recurso.setPersona(recursoDetails.getPersona());
            return recursoAcademicoRepository.save(recurso);
        }).orElseThrow(() -> new RuntimeException("Recurso académico no encontrado"));
    }

    // Eliminar un recurso académico
    public void deleteRecurso(Long id) {
        if (!recursoAcademicoRepository.existsById(id)) {
            throw new RuntimeException("Recurso académico no encontrado");
        }
        recursoAcademicoRepository.deleteById(id);
    }
}
