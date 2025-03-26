package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import java.util.List;
import java.util.Optional;

public interface MateriaService {

    Materia save(Materia materia);

    Materia update(Materia materia);

    void delete(Long id);

    Optional<Materia> findById(Long id);

    List<Materia> findAll();

    Optional<Materia> findByNombre(String nombre);

    List<Materia> findByProgramaId(Long programaId);

    List<Materia> findBySemestreId(Long semestreId);

    List<Materia> findMateriasElectivas();

    List<Materia> findMateriasObligatorias();

    List<Materia> findMateriasConPrerrequisito();

    List<Materia> findMateriasSinPrerrequisito();

    List<Materia> findMateriasConCupoDisponible();

    long countMateriasByProgramaId(Long programaId);
}
