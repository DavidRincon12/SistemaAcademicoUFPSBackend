package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecursoAcademicoRepository extends JpaRepository<RecursoAcademico, Long> {

    // Buscar por nombre
    List<RecursoAcademico> findByNombre(String nombre);

    // Buscar por ubicación
    List<RecursoAcademico> findByUbicacionRecurso(String ubicacion);

    // Buscar por persona responsable
    List<RecursoAcademico> findByPersonaId(Long personaId);

}
