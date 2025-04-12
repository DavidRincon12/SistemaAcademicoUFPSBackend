package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.AsignaturaEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaEstudianteRepository extends JpaRepository<AsignaturaEstudiante, Long> {

    List<AsignaturaEstudiante> findByEstudianteId(Long estudianteId);

    List<AsignaturaEstudiante> findByAsignaturaId(Long asignaturaId);
}
