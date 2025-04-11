package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    // Buscar un administrador por su persona (número de documento de la persona asociada)
    @Query("SELECT a FROM Administrador a WHERE a.persona.numeroDocumento = ?1")
    Optional<Administrador> findByNumeroDocumentoPersona(String numeroDocumento);

    // Buscar un administrador por su ID de persona
    Optional<Administrador> findByPersona_Id(Long personaId);
}
