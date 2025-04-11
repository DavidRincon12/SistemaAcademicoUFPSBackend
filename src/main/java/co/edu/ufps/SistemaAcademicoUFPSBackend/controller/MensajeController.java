package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public List<Mensaje> getAllMensajes() {
        return mensajeService.getAllMensajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody @Valid Mensaje mensaje) {
        Mensaje nuevo = mensajeService.createMensaje(mensaje);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<Void> editarMensaje(@PathVariable Long id, @RequestParam String nuevoContenido) {
        mensajeService.editarMensaje(id, nuevoContenido);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/eliminar-negocio")
    public ResponseEntity<Void> eliminarMensajeNegocio(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        mensajeService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok().build();
    }
}
