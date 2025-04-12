package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RecursoAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Crear un nuevo recurso académico
    @Transactional
    public RecursoAcademico createRecurso(RecursoAcademico recurso) {
        return recursoAcademicoRepository.save(recurso);
    }

    // Actualizar un recurso académico
    @Transactional
    public RecursoAcademico updateRecurso(Long id, RecursoAcademico recursoDetails) {
        RecursoAcademico recursoExistente = recursoAcademicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));

        // Actualizamos los detalles
        recursoExistente.setNombre(recursoDetails.getNombre());
        recursoExistente.setUbicacionRecurso(recursoDetails.getUbicacionRecurso());
        recursoExistente.setDescripcion(recursoDetails.getDescripcion());
        recursoExistente.setDisponible(recursoDetails.isDisponible());

        // Se actualiza el responsable si se pasa un nuevo responsable
        if (recursoDetails.getResponsable() != null) {
            recursoExistente.setResponsable(recursoDetails.getResponsable());
        }

        return recursoAcademicoRepository.save(recursoExistente);
    }

    // Eliminar un recurso académico
    @Transactional
    public void deleteRecurso(Long id) {
        RecursoAcademico recurso = recursoAcademicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));
        recursoAcademicoRepository.delete(recurso);
    }

    // ------------------------- Consultas Específicas -------------------------

    // Obtener recursos académicos por nombre
    @Transactional(readOnly = true)
    public List<RecursoAcademico> getRecursosByNombre(String nombre) {
        return recursoAcademicoRepository.findByNombre(nombre);
    }

    // Obtener recursos académicos por ubicación
    @Transactional(readOnly = true)
    public List<RecursoAcademico> getRecursosByUbicacion(String ubicacion) {
        return recursoAcademicoRepository.findByUbicacionRecurso(ubicacion);
    }

    // Obtener recursos académicos por ID de responsable
    @Transactional(readOnly = true)
    public List<RecursoAcademico> getRecursosByResponsableId(Long personaId) {
        return recursoAcademicoRepository.findByResponsable_Id(personaId);
    }

    // ------------------------- Métodos de Negocio -------------------------

    // Consultar disponibilidad de un recurso
    @Transactional(readOnly = true)
    public boolean consultarDisponibilidad(Long recursoId) {
        RecursoAcademico recurso = recursoAcademicoRepository.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + recursoId));
        return recurso.isDisponible();
    }

    // Cambiar disponibilidad de un recurso
    @Transactional
    public boolean cambiarDisponibilidad(Long recursoId, boolean nuevaDisponibilidad) {
        RecursoAcademico recurso = recursoAcademicoRepository.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + recursoId));

        recurso.setDisponible(nuevaDisponibilidad);
        recursoAcademicoRepository.save(recurso);
        return true;
    }

    // Verificar si el responsable tiene el rol requerido
    @Transactional(readOnly = true)
    public boolean verificarRolResponsable(Long recursoId, String rolRequerido) {
        RecursoAcademico recurso = recursoAcademicoRepository.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + recursoId));

        Persona responsable = recurso.getResponsable();
        return responsable != null && responsable.getRol().equals(rolRequerido);
    }
}
