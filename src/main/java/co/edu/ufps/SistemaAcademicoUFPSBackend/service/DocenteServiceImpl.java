package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;

    public DocenteServiceImpl(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Override
    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    public Docente update(Docente docente) {
        // Aquí podrías agregar validaciones o lógica adicional antes de actualizar
        return docenteRepository.save(docente);
    }

    @Override
    public void delete(Long id) {
        docenteRepository.deleteById(id);
    }

    @Override
    public Optional<Docente> findById(Long id) {
        return docenteRepository.findById(id);
    }

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> findByCorreoInstucional(String correoInstucional) {
        return docenteRepository.findByCorreoInstucional(correoInstucional);
    }

    @Override
    public List<Docente> findByTipo(String tipo) {
        return docenteRepository.findByTipo(tipo);
    }

    @Override
    public List<Docente> findDocentesConAsesoria() {
        return docenteRepository.findDocentesConAsesoria();
    }

    @Override
    public List<Docente> searchByNombre(String nombre) {
        return docenteRepository.searchByNombre(nombre);
    }
}
