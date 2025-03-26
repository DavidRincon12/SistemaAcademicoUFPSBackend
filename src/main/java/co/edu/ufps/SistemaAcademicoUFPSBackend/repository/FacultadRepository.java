package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {

    // Buscar facultad por nombre (sin importar mayúsculas/minúsculas)
    Optional<Facultad> findByNombreIgnoreCase(String nombre);

    // Obtener facultades que tengan un decano asignado
    @Query("SELECT f FROM Facultad f WHERE f.decano IS NOT NULL")
    List<Facultad> findFacultadesConDecano();

    // Buscar facultad por el ID del decano
    @Query("SELECT f FROM Facultad f WHERE f.decano.id = :decanoId")
    Optional<Facultad> findByDecanoId(@Param("decanoId") Long decanoId);

    // Buscar facultades cuyo nombre contenga cierto texto
    @Query("SELECT f FROM Facultad f WHERE LOWER(f.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Facultad> searchByNombre(@Param("nombre") String nombre);
}
