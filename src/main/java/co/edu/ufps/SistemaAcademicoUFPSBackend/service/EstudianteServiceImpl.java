package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante update(Estudiante estudiante) {
        // Aquí puedes agregar lógica adicional (validaciones, etc.) antes de actualizar
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void delete(Long id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> findByCorreoEstudiantil(String correoEstudiantil) {
        return estudianteRepository.findByCorreoEstudiantil(correoEstudiantil);
    }

    @Override
    public List<Estudiante> findByEstado(String estado) {
        return estudianteRepository.findByEstado(estado);
    }

    @Override
    public List<Estudiante> findEstudiantesConBecas() {
        return estudianteRepository.findEstudiantesConBecas();
    }

    @Override
    public List<Estudiante> findByPrograma(Long programaId) {
        return estudianteRepository.findByPrograma(programaId);
    }

    @Override
    public List<Estudiante> searchByNombre(String nombre) {
        return estudianteRepository.searchByNombre(nombre);
    }
}
