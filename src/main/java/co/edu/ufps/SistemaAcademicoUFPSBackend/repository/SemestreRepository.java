package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    // Buscar un semestre por su nombre
    Optional<Semestre> findByNombre(String nombre);

    // Buscar el semestre actual basado en la fecha actual
    Optional<Semestre> findByFechaInicioBeforeAndFechaFinAfter(java.util.Date fechaInicio, java.util.Date fechaFin);
}
