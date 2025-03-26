package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Administrador;
import java.util.List;
import java.util.Optional;

public interface AdministradorService {

    Administrador save(Administrador administrador);

    Administrador update(Administrador administrador);

    void delete(Long id);

    Optional<Administrador> findById(Long id);

    List<Administrador> findAll();

    Optional<Administrador> findByNumeroDocumentoPersona(String numeroDocumento);

    Optional<Administrador> findByPersonaId(Long personaId);

    Optional<Administrador> findByCalendarioId(Long calendarioId);
}
