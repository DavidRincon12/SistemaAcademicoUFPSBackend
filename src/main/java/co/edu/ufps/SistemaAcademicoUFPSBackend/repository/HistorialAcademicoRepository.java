package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistorialAcademicoRepository extends JpaRepository<HistorialAcademico, Long> {

    // Buscar historial por el ID del estudiante
    Optional<HistorialAcademico> findByEstudianteId(Long estudianteId);

    // Obtener el promedio ponderado de un estudiante
    @Query("SELECT h.promedioPonderado FROM HistorialAcademico h WHERE h.estudiante.id = :estudianteId")
    Float findPromedioPonderadoByEstudianteId(@Param("estudianteId") Long estudianteId);

    // Contar cuántas materias ha aprobado un estudiante
    @Query("SELECT SIZE(h.materiasAprobadas) FROM HistorialAcademico h WHERE h.estudiante.id = :estudianteId")
    int countMateriasAprobadasByEstudianteId(@Param("estudianteId") Long estudianteId);

    // Contar cuántas materias está cursando actualmente un estudiante
    @Query("SELECT SIZE(h.materiasProceso) FROM HistorialAcademico h WHERE h.estudiante.id = :estudianteId")
    int countMateriasProcesoByEstudianteId(@Param("estudianteId") Long estudianteId);
}
