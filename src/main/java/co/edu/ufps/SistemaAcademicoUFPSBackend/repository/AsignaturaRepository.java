package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    // Buscar asignatura por nombre
    Optional<Asignatura> findByNombre(String nombre);

    // Buscar todas las asignaturas de un docente específico
    @Query("SELECT a FROM Asignatura a WHERE a.docente.id = ?1")
    List<Asignatura> findByDocenteId(Long docenteId);

    // Buscar todas las asignaturas de una materia específica
    @Query("SELECT a FROM Asignatura a WHERE a.materia.id = ?1")
    List<Asignatura> findByMateriaId(Long materiaId);

    // Buscar todas las asignaturas de un estudiante específico
    @Query("SELECT a FROM Asignatura a JOIN a.estudiantes e WHERE e.id = ?1")
    List<Asignatura> findByEstudianteId(Long estudianteId);

    // Buscar asignaturas con habilitación
    List<Asignatura> findByHabilitacionTrue();

    // Buscar asignaturas vacacionales
    List<Asignatura> findByVacacionalTrue();
}
