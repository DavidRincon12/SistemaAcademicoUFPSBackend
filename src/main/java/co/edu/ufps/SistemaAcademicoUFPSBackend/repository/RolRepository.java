package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Buscar roles por nombre
    List<Rol> findByNombre(String nombre);

    // Buscar roles por persona
    List<Rol> findByPersonas_Id(Long personaId);
}
