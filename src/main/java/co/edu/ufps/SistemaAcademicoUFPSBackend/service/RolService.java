package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolService {

    Rol save(Rol rol);

    Rol update(Rol rol);

    void delete(Long id);

    Optional<Rol> findById(Long id);

    List<Rol> findAll();

    List<Rol> findByNombre(String nombre);

    List<Rol> findByPersonaId(Long personaId);
}
