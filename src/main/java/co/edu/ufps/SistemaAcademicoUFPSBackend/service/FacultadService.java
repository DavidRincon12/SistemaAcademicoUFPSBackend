package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import java.util.List;
import java.util.Optional;

public interface FacultadService {

    Facultad save(Facultad facultad);

    Facultad update(Facultad facultad);

    void delete(Long id);

    Optional<Facultad> findById(Long id);

    List<Facultad> findAll();

    Optional<Facultad> findByNombreIgnoreCase(String nombre);

    List<Facultad> findFacultadesConDecano();

    Optional<Facultad> findByDecanoId(Long decanoId);

    List<Facultad> searchByNombre(String nombre);
}
