package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ForoService {

    Foro save(Foro foro);

    Foro update(Foro foro);

    void delete(Long id);

    Optional<Foro> findById(Long id);

    List<Foro> findAll();

    List<Foro> findByTemaIgnoreCase(String tema);

    List<Foro> findByAutorId(Long autorId);

    List<Foro> findByFechaCreacionAfter(Date fecha);

    List<Foro> searchByDescripcion(String descripcion);

    long countForosByAutor(Long autorId);
}
