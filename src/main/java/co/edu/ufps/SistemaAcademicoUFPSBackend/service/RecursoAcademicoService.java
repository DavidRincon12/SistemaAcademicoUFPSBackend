package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import java.util.List;
import java.util.Optional;

public interface RecursoAcademicoService {

    RecursoAcademico save(RecursoAcademico recursoAcademico);

    RecursoAcademico update(RecursoAcademico recursoAcademico);

    void delete(Long id);

    Optional<RecursoAcademico> findById(Long id);

    List<RecursoAcademico> findAll();

    List<RecursoAcademico> findByNombre(String nombre);

    List<RecursoAcademico> findByUbicacionRecurso(String ubicacion);

    List<RecursoAcademico> findByPersonaId(Long personaId);
}
