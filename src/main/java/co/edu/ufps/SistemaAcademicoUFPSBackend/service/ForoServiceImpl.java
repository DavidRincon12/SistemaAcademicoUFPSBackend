package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ForoRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ForoServiceImpl implements ForoService {

    private final ForoRepository foroRepository;

    public ForoServiceImpl(ForoRepository foroRepository) {
        this.foroRepository = foroRepository;
    }

    @Override
    public Foro save(Foro foro) {
        return foroRepository.save(foro);
    }

    @Override
    public Foro update(Foro foro) {
        // Puedes agregar validaciones o lógica adicional aquí antes de actualizar
        return foroRepository.save(foro);
    }

    @Override
    public void delete(Long id) {
        foroRepository.deleteById(id);
    }

    @Override
    public Optional<Foro> findById(Long id) {
        return foroRepository.findById(id);
    }

    @Override
    public List<Foro> findAll() {
        return foroRepository.findAll();
    }

    @Override
    public List<Foro> findByTemaIgnoreCase(String tema) {
        return foroRepository.findByTemaIgnoreCase(tema);
    }

    @Override
    public List<Foro> findByAutorId(Long autorId) {
        return foroRepository.findByAutorId(autorId);
    }

    @Override
    public List<Foro> findByFechaCreacionAfter(Date fecha) {
        return foroRepository.findByFechaCreacionAfter(fecha);
    }

    @Override
    public List<Foro> searchByDescripcion(String descripcion) {
        return foroRepository.searchByDescripcion(descripcion);
    }

    @Override
    public long countForosByAutor(Long autorId) {
        return foroRepository.countForosByAutor(autorId);
    }
}
