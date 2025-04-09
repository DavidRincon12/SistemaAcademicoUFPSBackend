package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Administrador;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Obtener todos los administradores
    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorService.getAllAdministrators();
    }

    // Obtener un administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        Optional<Administrador> administrador = administradorService.getAdministratorById(id);
        return administrador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo administrador
    @PostMapping
    public ResponseEntity<Administrador> createAdministrador(@RequestBody @Valid Administrador administrador) {
        return ResponseEntity.ok(administradorService.createAdministrator(administrador));
    }

    // Actualizar un administrador existente
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administradorDetails) {
        try {
            Administrador updatedAdministrador = administradorService.updateAdministrator(id, administradorDetails);
            return ResponseEntity.ok(updatedAdministrador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Long id) {
        try {
            administradorService.deleteAdministrator(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
