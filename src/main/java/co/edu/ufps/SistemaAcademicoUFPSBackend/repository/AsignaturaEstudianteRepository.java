package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.AsignaturaEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignaturaEstudianteRepository extends JpaRepository<AsignaturaEstudiante, Long> {

    // Buscar inscripción por estudiante y asignatura
    Optional<AsignaturaEstudiante> findByEstudianteIdAndAsignaturaId(Long idEstudiante, Long idAsignatura);

    // Buscar todas las inscripciones de un estudiante
    List<AsignaturaEstudiante> findByEstudianteId(Long idEstudiante);

    // Buscar todas las inscripciones de una asignatura
    List<AsignaturaEstudiante> findByAsignaturaId(Long idAsignatura);
}
