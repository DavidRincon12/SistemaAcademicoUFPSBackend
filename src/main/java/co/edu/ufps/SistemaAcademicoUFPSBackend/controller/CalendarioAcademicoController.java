package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.CalendarioAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.CalendarioAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendarios")
@CrossOrigin(origins = "*")
public class CalendarioAcademicoController {

    @Autowired
    private CalendarioAcademicoService calendarioService;

    // Obtener todos los calendarios
    @GetMapping
    public ResponseEntity<List<CalendarioAcademico>> getAllCalendarios() {
        return ResponseEntity.ok(calendarioService.getAllCalendarios());
    }

    // Obtener calendario por ID
    @GetMapping("/{id}")
    public ResponseEntity<CalendarioAcademico> getCalendarioById(@PathVariable Long id) {
        return calendarioService.getCalendarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo calendario
    @PostMapping
    public ResponseEntity<CalendarioAcademico> createCalendario(@RequestBody CalendarioAcademico calendario) {
        return ResponseEntity.ok(calendarioService.createCalendario(calendario));
    }

    // Actualizar calendario
    @PutMapping("/{id}")
    public ResponseEntity<CalendarioAcademico> updateCalendario(@PathVariable Long id,
                                                                @RequestBody CalendarioAcademico calendarioDetails) {
        try {
            CalendarioAcademico actualizado = calendarioService.updateCalendario(id, calendarioDetails);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar calendario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendario(@PathVariable Long id) {
        try {
            calendarioService.deleteCalendario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por nombre del período
    @GetMapping("/buscar/nombrePeriodo")
    public ResponseEntity<Optional<CalendarioAcademico>> findByNombrePeriodo(@RequestParam String nombrePeriodo) {
        return ResponseEntity.ok(calendarioService.findByNombrePeriodo(nombrePeriodo));
    }

    // Buscar si una fecha está dentro del periodo
    @GetMapping("/buscar/fecha")
    public ResponseEntity<List<CalendarioAcademico>> findByFecha(@RequestParam Date fecha) {
        return ResponseEntity.ok(calendarioService.findByFechaDentroDelPeriodo(fecha));
    }

    // Buscar calendarios por un rango de fechas
    @GetMapping("/buscar/rangoFechas")
    public ResponseEntity<List<CalendarioAcademico>> findByRangoFechas(
            @RequestParam Date fechaInicio,
            @RequestParam Date fechaFin) {
        return ResponseEntity.ok(calendarioService.findByPeriodoEntreFechas(fechaInicio, fechaFin));
    }

    // Buscar el calendario activo (por fecha actual)
    @GetMapping("/activo")
    public ResponseEntity<Optional<CalendarioAcademico>> findCalendarioActivo() {
        return ResponseEntity.ok(calendarioService.findCalendarioActivo());
    }
}
