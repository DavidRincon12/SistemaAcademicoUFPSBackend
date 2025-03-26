package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Override
    public Asignatura update(Asignatura asignatura) {
        // Aquí podrías incluir lógica adicional de validación o transformación
        return asignaturaRepository.save(asignatura);
    }

    @Override
    public void delete(Long id) {
        asignaturaRepository.deleteById(id);
    }

    @Override
    public Optional<Asignatura> findById(Long id) {
        return asignaturaRepository.findById(id);
    }

    @Override
    public List<Asignatura> findAll() {
        return asignaturaRepository.findAll();
    }

    @Override
    public Optional<Asignatura> findByNombre(String nombre) {
        return asignaturaRepository.findByNombre(nombre);
    }

    @Override
    public List<Asignatura> findByDocenteId(Long docenteId) {
        return asignaturaRepository.findByDocenteId(docenteId);
    }

    @Override
    public List<Asignatura> findByMateriaId(Long materiaId) {
        return asignaturaRepository.findByMateriaId(materiaId);
    }

    @Override
    public List<Asignatura> findByEstudianteId(Long estudianteId) {
        return asignaturaRepository.findByEstudianteId(estudianteId);
    }

    @Override
    public List<Asignatura> findAsignaturasHabilitadas() {
        return asignaturaRepository.findByHabilitacionTrue();
    }

    @Override
    public List<Asignatura> findAsignaturasVacacionales() {
        return asignaturaRepository.findByVacacionalTrue();
    }
}
