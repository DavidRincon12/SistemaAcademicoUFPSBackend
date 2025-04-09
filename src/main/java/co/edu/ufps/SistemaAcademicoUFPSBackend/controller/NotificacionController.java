package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Notificacion;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // Obtener todas las notificaciones
    @GetMapping
    public ResponseEntity<List<Notificacion>> getAllNotificaciones() {
        return ResponseEntity.ok(notificacionService.getAllNotificaciones());
    }

    // Obtener notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        Optional<Notificacion> notificacion = notificacionService.getNotificacionById(id);
        return notificacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear nueva notificación
    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        try {
            return ResponseEntity.ok(notificacionService.createNotificacion(notificacion));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).body(null);
        }
    }

    // Actualizar notificación
    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        try {
            return ResponseEntity.ok(notificacionService.updateNotificacion(id, notificacion));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).body(null);
        }
    }

    // Eliminar notificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        try {
            notificacionService.deleteNotificacion(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Obtener notificaciones por destinatario
    @GetMapping("/destinatario/{destinatarioId}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByDestinatarioId(@PathVariable Long destinatarioId) {
        try {
            return ResponseEntity.ok(notificacionService.getNotificacionesByDestinatarioId(destinatarioId));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).body(null);
        }
    }

    // Obtener no leídas por destinatario
    @GetMapping("/no-leidas/{destinatarioId}")
    public ResponseEntity<List<Notificacion>> getNotificacionesNoLeidas(@PathVariable Long destinatarioId) {
        try {
            return ResponseEntity.ok(notificacionService.getNotificacionesNoLeidas(destinatarioId));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).body(null);
        }
    }

    // Contar no leídas por destinatario
    @GetMapping("/count-no-leidas/{destinatarioId}")
    public ResponseEntity<Long> countNotificacionesNoLeidas(@PathVariable Long destinatarioId) {
        try {
            return ResponseEntity.ok(notificacionService.countNotificacionesNoLeidas(destinatarioId));
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).body(0L);
        }
    }

    // Marcar como leída
    @PutMapping("/marcar-leida/{id}")
    public ResponseEntity<Void> marcarComoLeido(@PathVariable Long id) {
        try {
            notificacionService.marcarComoLeido(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    // Eliminar notificación lógicamente
    @DeleteMapping("/logica/{id}")
    public ResponseEntity<Void> eliminarNotificacionLogica(@PathVariable Long id) {
        try {
            notificacionService.eliminarNotificacionLogica(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
