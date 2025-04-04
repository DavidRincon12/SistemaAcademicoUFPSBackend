package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Rol;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    // Obtener todos los roles

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    // Obtener un rol por ID
    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    @Transactional
    public Rol createRol(Rol rol) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Rol updateRol(Long id, Rol rolDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteRol(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Rol> getRolesByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Rol> getRolesByPersonaId(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void agregarPermiso(Long rolId, String permiso) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void revocarPermiso(Long rolId, String permiso) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void asignarRolAPersona(Long rolId, Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void removerRolDePersona(Long rolId, Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}