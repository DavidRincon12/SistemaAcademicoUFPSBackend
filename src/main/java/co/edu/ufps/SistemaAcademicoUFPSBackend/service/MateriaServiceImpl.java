package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public Materia update(Materia materia) {
        // Se pueden agregar validaciones o lógica adicional antes de actualizar
        return materiaRepository.save(materia);
    }

    @Override
    public void delete(Long id) {
        materiaRepository.deleteById(id);
    }

    @Override
    public Optional<Materia> findById(Long id) {
        return materiaRepository.findById(id);
    }

    @Override
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    @Override
    public Optional<Materia> findByNombre(String nombre) {
        return materiaRepository.findByNombre(nombre);
    }

    @Override
    public List<Materia> findByProgramaId(Long programaId) {
        return materiaRepository.findByProgramaId(programaId);
    }

    @Override
    public List<Materia> findBySemestreId(Long semestreId) {
        return materiaRepository.findBySemestreId(semestreId);
    }

    @Override
    public List<Materia> findMateriasElectivas() {
        // Las materias electivas son aquellas donde el atributo electiva es true
        return materiaRepository.findByElectivaTrue();
    }

    @Override
    public List<Materia> findMateriasObligatorias() {
        // Las materias obligatorias son aquellas donde el atributo electiva es false
        return materiaRepository.findByElectivaFalse();
    }

    @Override
    public List<Materia> findMateriasConPrerrequisito() {
        return materiaRepository.findMateriasConPrerrequisito();
    }

    @Override
    public List<Materia> findMateriasSinPrerrequisito() {
        return materiaRepository.findMateriasSinPrerrequisito();
    }

    @Override
    public List<Materia> findMateriasConCupoDisponible() {
        return materiaRepository.findMateriasConCupoDisponible();
    }

    @Override
    public long countMateriasByProgramaId(Long programaId) {
        return materiaRepository.countMateriasByProgramaId(programaId);
    }
}
