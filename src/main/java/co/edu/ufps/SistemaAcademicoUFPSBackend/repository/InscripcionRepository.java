package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Inscripcion;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    Optional<Inscripcion> findByEstudianteAndClaseAndCanceladaFalse(Estudiante estudiante, Clase clase);

    List<Inscripcion> findByClaseAndCanceladaFalse(Clase clase);

    List<Inscripcion> findByEstudianteAndCanceladaFalse(Estudiante estudiante);
}
