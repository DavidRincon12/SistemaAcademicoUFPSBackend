package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Administrador;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    // Obtener todos los administradores
    public List<Administrador> getAllAdministrators() {
        return administradorRepository.findAll();
    }

    // Obtener un administrador por ID
    public Optional<Administrador> getAdministratorById(Long id) {
        return administradorRepository.findById(id);
    }

    // Crear un nuevo administrador
    public Administrador createAdministrator(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    // Actualizar administrador
    public Administrador updateAdministrator(Long id, Administrador administradorDetails) {
        return administradorRepository.findById(id).map(admin -> {
            admin.setPersona(administradorDetails.getPersona());
            admin.setCalendario(administradorDetails.getCalendario());
            return administradorRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    // Eliminar administrador
    public void deleteAdministrator(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new RuntimeException("Administrador no encontrado");
        }
        administradorRepository.deleteById(id);
    }

    // Métodos adicionales requeridos

    public Optional<Administrador> findByNumeroDocumentoPersona(String numeroDocumento) {
        return administradorRepository.findByNumeroDocumentoPersona(numeroDocumento);
    }

    public Optional<Administrador> findByPersonaId(Long personaId) {
        return administradorRepository.findByPersona_Id(personaId);
    }

    public Optional<Administrador> findByCalendarioId(Long calendarioId) {
        return administradorRepository.findByCalendario_Id(calendarioId);
    }
}
