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
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // ---------------------- Endpoints CRUD Básicos ----------------------

    // Obtener todos los mensajes
    @GetMapping
    public List<Mensaje> getAllMensajes() {
        return mensajeService.getAllMensajes();
    }

    // Obtener un mensaje por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------------- Endpoints para Consultas Específicas ----------------------

    // Obtener mensajes de un chat ordenados por fecha de envío
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Mensaje>> getMensajesByChatId(@PathVariable Long chatId) {
        try {
            List<Mensaje> mensajes = mensajeService.getMensajesByChatId(chatId);
            return ResponseEntity.ok(mensajes);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Obtener mensajes enviados por un usuario (emisor)
    @GetMapping("/emisor/{emisorId}")
    public ResponseEntity<List<Mensaje>> getMensajesByEmisorId(@PathVariable Long emisorId) {
        try {
            List<Mensaje> mensajes = mensajeService.getMensajesByEmisorId(emisorId);
            return ResponseEntity.ok(mensajes);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Obtener mensajes recibidos por un usuario (destinatario)
    @GetMapping("/destinatario/{destinatarioId}")
    public ResponseEntity<List<Mensaje>> getMensajesByDestinatarioId(@PathVariable Long destinatarioId) {
        try {
            List<Mensaje> mensajes = mensajeService.getMensajesByDestinatarioId(destinatarioId);
            return ResponseEntity.ok(mensajes);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Obtener mensajes entre dos usuarios en un chat específico
    @GetMapping("/chat/{chatId}/entre")
    public ResponseEntity<List<Mensaje>> getMensajesEntreUsuariosEnChat(@PathVariable Long chatId,
                                                                        @RequestParam Long usuario1,
                                                                        @RequestParam Long usuario2) {
        try {
            List<Mensaje> mensajes = mensajeService.getMensajesEntreUsuariosEnChat(chatId, usuario1, usuario2);
            return ResponseEntity.ok(mensajes);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Obtener mensajes no leídos de un usuario en un chat
    @GetMapping("/chat/{chatId}/no-leidos")
    public ResponseEntity<List<Mensaje>> getMensajesNoLeidos(@PathVariable Long chatId,
                                                             @RequestParam Long destinatarioId) {
        try {
            List<Mensaje> mensajes = mensajeService.getMensajesNoLeidos(chatId, destinatarioId);
            return ResponseEntity.ok(mensajes);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Contar cuántos mensajes no leídos tiene un usuario en un chat
    @GetMapping("/chat/{chatId}/no-leidos/count")
    public ResponseEntity<Long> countMensajesNoLeidos(@PathVariable Long chatId,
                                                      @RequestParam Long destinatarioId) {
        try {
            long count = mensajeService.countMensajesNoLeidos(chatId, destinatarioId);
            return ResponseEntity.ok(count);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // ---------------------- Endpoints para Métodos de Negocio (Pendientes) ----------------------

    // Endpoint para editar un mensaje
    @PutMapping("/{id}/editar")
    public ResponseEntity<Void> editarMensaje(@PathVariable Long id, @RequestParam String nuevoContenido) {
        try {
            mensajeService.editarMensaje(id, nuevoContenido);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // 501 Not Implemented
        }
    }

    // Endpoint para eliminar un mensaje mediante lógica de negocio
    @DeleteMapping("/{id}/eliminar-negocio")
    public ResponseEntity<Void> eliminarMensajeNegocio(@PathVariable Long id) {
        try {
            mensajeService.eliminarMensaje(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Endpoint para cambiar el estado de un mensaje
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        try {
            mensajeService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
