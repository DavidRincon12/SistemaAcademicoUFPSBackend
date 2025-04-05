package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Rol;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService {

    @Autowired
    private final RolRepository rolRepository;

    // Obtener todos los roles
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    // Obtener un rol por ID
    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    // Crear un nuevo rol
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Actualizar un rol existente
    public Rol updateRol(Long id, Rol rolDetails) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(rolDetails.getNombre());
            rol.setPermisos(rolDetails.getPermisos());
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