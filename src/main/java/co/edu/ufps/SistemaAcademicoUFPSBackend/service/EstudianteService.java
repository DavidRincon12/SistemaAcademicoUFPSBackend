package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    Estudiante save(Estudiante estudiante);

    Estudiante update(Estudiante estudiante);

    void delete(Long id);

    Optional<Estudiante> findById(Long id);

    List<Estudiante> findAll();

    Optional<Estudiante> findByCorreoEstudiantil(String correoEstudiantil);

    List<Estudiante> findByEstado(String estado);

    List<Estudiante> findEstudiantesConBecas();

    List<Estudiante> findByPrograma(Long programaId);

    List<Estudiante> searchByNombre(String nombre);
}
