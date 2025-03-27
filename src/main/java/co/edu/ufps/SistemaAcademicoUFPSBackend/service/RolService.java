package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Rol;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Buscar roles por nombre
    public List<Rol> getRolesByNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    // Buscar roles por persona
    public List<Rol> getRolesByPersonaId(Long personaId) {
        return rolRepository.findByPersonaId(personaId);
    }

    // Crear un nuevo rol
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Actualizar un rol existente
    public Rol updateRol(Long id, Rol rolDetails) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(rolDetails.getNombre());
            rol.setPersona(rolDetails.getPersona());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    // Eliminar un rol
    public void deleteRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado");
        }
        rolRepository.deleteById(id);
    }
}
