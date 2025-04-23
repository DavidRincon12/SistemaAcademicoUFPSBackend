package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialAcademicoRepository extends JpaRepository<HistorialAcademico, Long> {

    // Obtener todos los registros de historial académico de un estudiante
    List<HistorialAcademico> findByEstudianteId(Long estudianteId);

    // Obtener historial de un estudiante por estado (ej: APROBADO, EN_CURSO)
    List<HistorialAcademico> findByEstudianteIdAndEstado(Long estudianteId, EstadoCurso estado);

    // Calcular el promedio ponderado del estudiante (asumiendo que solo se promedian los cursos aprobados)
    @Query("SELECT AVG(h.nota) FROM HistorialAcademico h WHERE h.estudiante.id = :estudianteId AND h.estado = 'APROBADO'")
    Float calcularPromedioPonderado(@Param("estudianteId") Long estudianteId);

    // Contar asignaturas aprobadas por un estudiante
    @Query("SELECT COUNT(h) FROM HistorialAcademico h WHERE h.estudiante.id = :estudianteId AND h.estado = 'APROBADO'")
    int countMateriasAprobadas(@Param("estudianteId") Long estudianteId);

    // Contar asignaturas en curso por estudiante
    @Query("SELECT COUNT(h) FROM HistorialAcademico h WHERE h.estudiante.id = :estudianteId AND h.estado = 'EN_CURSO'")
    int countMateriasEnCurso(@Param("estudianteId") Long estudianteId);
}
