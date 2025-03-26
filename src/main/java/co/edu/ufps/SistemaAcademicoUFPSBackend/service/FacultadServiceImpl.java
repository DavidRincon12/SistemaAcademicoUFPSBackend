package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.FacultadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadServiceImpl implements FacultadService {

    private final FacultadRepository facultadRepository;

    public FacultadServiceImpl(FacultadRepository facultadRepository) {
        this.facultadRepository = facultadRepository;
    }

    @Override
    public Facultad save(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public Facultad update(Facultad facultad) {
        // Puedes agregar lógica adicional o validaciones aquí antes de actualizar
        return facultadRepository.save(facultad);
    }

    @Override
    public void delete(Long id) {
        facultadRepository.deleteById(id);
    }

    @Override
    public Optional<Facultad> findById(Long id) {
        return facultadRepository.findById(id);
    }

    @Override
    public List<Facultad> findAll() {
        return facultadRepository.findAll();
    }

    @Override
    public Optional<Facultad> findByNombreIgnoreCase(String nombre) {
        return facultadRepository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public List<Facultad> findFacultadesConDecano() {
        return facultadRepository.findFacultadesConDecano();
    }

    @Override
    public Optional<Facultad> findByDecanoId(Long decanoId) {
        return facultadRepository.findByDecanoId(decanoId);
    }

    @Override
    public List<Facultad> searchByNombre(String nombre) {
        return facultadRepository.searchByNombre(nombre);
    }
}
