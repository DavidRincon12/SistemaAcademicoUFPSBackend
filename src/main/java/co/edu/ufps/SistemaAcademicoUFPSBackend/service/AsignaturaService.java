package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import java.util.List;
import java.util.Optional;

public interface AsignaturaService {

    Asignatura save(Asignatura asignatura);

    Asignatura update(Asignatura asignatura);

    void delete(Long id);

    Optional<Asignatura> findById(Long id);

    List<Asignatura> findAll();

    Optional<Asignatura> findByNombre(String nombre);

    List<Asignatura> findByDocenteId(Long docenteId);

    List<Asignatura> findByMateriaId(Long materiaId);

    List<Asignatura> findByEstudianteId(Long estudianteId);

    List<Asignatura> findAsignaturasHabilitadas();

    List<Asignatura> findAsignaturasVacacionales();
}
