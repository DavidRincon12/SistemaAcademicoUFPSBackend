package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Buscar un estudiante por su correo estudiantil
    Optional<Estudiante> findByCorreoEstudiantil(String correoEstudiantil);

    // Buscar estudiantes por estado (Ejemplo: activo, inactivo, egresado)
    List<Estudiante> findByEstado(String estado);

    // Obtener estudiantes con becas
    @Query("SELECT e FROM Estudiante e WHERE e.becas IS NOT NULL AND e.becas <> ''")
    List<Estudiante> findEstudiantesConBecas();

    // Buscar estudiantes de un programa específico
    @Query("SELECT e FROM Estudiante e WHERE e.programa.id = :programaId")
    List<Estudiante> findByPrograma(@Param("programaId") Long programaId);

    // Buscar estudiantes por nombre sin importar mayúsculas/minúsculas
    @Query("SELECT e FROM Estudiante e WHERE LOWER(e.persona.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Estudiante> searchByNombre(@Param("nombre") String nombre);
}
