package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Administrador;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AdministradorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorServiceImpl(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador update(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public void delete(Long id) {
        administradorRepository.deleteById(id);
    }

    @Override
    public Optional<Administrador> findById(Long id) {
        return administradorRepository.findById(id);
    }

    @Override
    public List<Administrador> findAll() {
        return administradorRepository.findAll();
    }

    @Override
    public Optional<Administrador> findByNumeroDocumentoPersona(String numeroDocumento) {
        return administradorRepository.findByNumeroDocumentoPersona(numeroDocumento);
    }

    @Override
    public Optional<Administrador> findByPersonaId(Long personaId) {
        return administradorRepository.findByPersona_Id(personaId);
    }

    @Override
    public Optional<Administrador> findByCalendarioId(Long calendarioId) {
        return administradorRepository.findByCalendario_Id(calendarioId);
    }
}
