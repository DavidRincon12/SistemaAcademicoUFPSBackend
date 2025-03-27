package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PersonalAdministrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalAdministrativoRepository extends JpaRepository<PersonalAdministrativo, Long> {

    // Buscar por cargo
    List<PersonalAdministrativo> findByCargo(String cargo);

    // Buscar por departamento
    List<PersonalAdministrativo> findByDepartamento(String departamento);

    // Buscar por área de trabajo
    List<PersonalAdministrativo> findByAreaTrabajo(String areaTrabajo);

    // Buscar por persona asociada
    Optional<PersonalAdministrativo> findByPersonaId(Long personaId);

}
