package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import java.util.List;
import java.util.Optional;

public interface DocenteService {

    Docente save(Docente docente);

    Docente update(Docente docente);

    void delete(Long id);

    Optional<Docente> findById(Long id);

    List<Docente> findAll();

    Optional<Docente> findByCorreoInstucional(String correoInstucional);

    List<Docente> findByTipo(String tipo);

    List<Docente> findDocentesConAsesoria();

    List<Docente> searchByNombre(String nombre);
}
