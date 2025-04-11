package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HistorialAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiales")
@CrossOrigin(origins = "*")
public class HistorialAcademicoController {

    @Autowired
    private HistorialAcademicoService historialService;

    // Obtener todos los historiales académicos
    @GetMapping
    public ResponseEntity<List<HistorialAcademico>> getAllHistoriales() {
        List<HistorialAcademico> historiales = historialService.findAll();
        return ResponseEntity.ok(historiales);
    }

    // Obtener un historial académico por ID
    @GetMapping("/{id}")
    public ResponseEntity<HistorialAcademico> getHistorialById(@PathVariable Long id) {
        HistorialAcademico historial = historialService.findById(id);
        if (historial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historial);
    }

    // Crear un nuevo historial académico
    @PostMapping
    public ResponseEntity<HistorialAcademico> createHistorial(@RequestBody HistorialAcademico historial) {
        HistorialAcademico nuevo = historialService.save(historial);
        return ResponseEntity.ok(nuevo);
    }

    // Actualizar un historial académico existente
    @PutMapping("/{id}")
    public ResponseEntity<HistorialAcademico> updateHistorial(@PathVariable Long id, @RequestBody HistorialAcademico actualizado) {
        HistorialAcademico historialActualizado = historialService.update(id, actualizado);
        if (historialActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historialActualizado);
    }

    // Eliminar un historial académico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable Long id) {
        historialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Agregar una asignatura aprobada
    @PostMapping("/{historialId}/aprobadas/{asignaturaId}")
    public ResponseEntity<HistorialAcademico> agregarAsignaturaAprobada(@PathVariable Long historialId, @PathVariable Long asignaturaId) {
        try {
            HistorialAcademico actualizado = historialService.agregarAsignaturaAprobada(historialId, asignaturaId);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Agregar una asignatura en proceso
    @PostMapping("/{historialId}/proceso/{asignaturaId}")
    public ResponseEntity<HistorialAcademico> agregarAsignaturaEnProceso(@PathVariable Long historialId, @PathVariable Long asignaturaId) {
        try {
            HistorialAcademico actualizado = historialService.agregarAsignaturaEnProceso(historialId, asignaturaId);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
