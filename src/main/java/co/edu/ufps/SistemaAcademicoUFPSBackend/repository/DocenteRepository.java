package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    // Buscar un docente por su correo institucional
    Optional<Docente> findByCorreoInstitucional(String correoInstitucional);

    // Obtener docentes por tipo (Ejemplo: planta, catedrático)
    List<Docente> findByTipo(String tipo);

    // Buscar docentes con un nombre específico
    @Query("SELECT d FROM Docente d WHERE LOWER(d.persona.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Docente> searchByNombre(@Param("nombre") String nombre);
}
