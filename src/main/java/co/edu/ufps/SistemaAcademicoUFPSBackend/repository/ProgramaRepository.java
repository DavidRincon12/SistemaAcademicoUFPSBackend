package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {

    // Buscar un programa por su nombre
    Optional<Programa> findByNombre(String nombre);

    // Buscar un programa por su código
    Optional<Programa> findByCodigo(String codigo);

    // Obtener todos los programas de una facultad
    List<Programa> findByFacultadId(Long facultadId);
}
