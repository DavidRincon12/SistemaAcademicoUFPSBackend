package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Administrador;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public boolean registrarUsuario(Persona persona) {
        // Lógica pendiente
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public boolean actualizarInformacion(Long adminId, Persona nuevaPersona) {
        // Lógica pendiente
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public boolean eliminarUsuario(Long adminId) {
        // Lógica pendiente
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public boolean asignarPrivilegios(Long adminId, String privilegios) {
        // Lógica pendiente
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    public Optional<Administrador> findByNumeroDocumentoPersona(String numeroDocumento) {
        // Lógica pendiente
        throw new UnsupportedOperationException("Método no implementado");
    }

    public Optional<Administrador> findByPersonaId(Long personaId) {
        // Lógica pendiente
        throw new UnsupportedOperationException("Método no implementado");
    }

}