package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RecursoAcademicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoAcademicoServiceImpl implements RecursoAcademicoService {

    private final RecursoAcademicoRepository recursoAcademicoRepository;

    public RecursoAcademicoServiceImpl(RecursoAcademicoRepository recursoAcademicoRepository) {
        this.recursoAcademicoRepository = recursoAcademicoRepository;
    }

    @Override
    public RecursoAcademico save(RecursoAcademico recursoAcademico) {
        return recursoAcademicoRepository.save(recursoAcademico);
    }

    @Override
    public RecursoAcademico update(RecursoAcademico recursoAcademico) {
        // Puedes agregar lógica adicional o validaciones antes de actualizar
        return recursoAcademicoRepository.save(recursoAcademico);
    }

    @Override
    public void delete(Long id) {
        recursoAcademicoRepository.deleteById(id);
    }

    @Override
    public Optional<RecursoAcademico> findById(Long id) {
        return recursoAcademicoRepository.findById(id);
    }

    @Override
    public List<RecursoAcademico> findAll() {
        return recursoAcademicoRepository.findAll();
    }

    @Override
    public List<RecursoAcademico> findByNombre(String nombre) {
        return recursoAcademicoRepository.findByNombre(nombre);
    }

    @Override
    public List<RecursoAcademico> findByUbicacionRecurso(String ubicacion) {
        return recursoAcademicoRepository.findByUbicacionRecurso(ubicacion);
    }

    @Override
    public List<RecursoAcademico> findByPersonaId(Long personaId) {
        return recursoAcademicoRepository.findByPersonaId(personaId);
    }
}
