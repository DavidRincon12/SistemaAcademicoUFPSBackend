package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.HistorialAcademicoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HistorialAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historiales")
@CrossOrigin(origins = "*")
public class HistorialAcademicoController {

    @Autowired
    private HistorialAcademicoService historialService;

    @GetMapping
    public ResponseEntity<List<HistorialAcademicoDTO>> getAllHistoriales() {
        var historiales = historialService.findAll().stream()
                .map(HistorialAcademicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(historiales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialAcademicoDTO> getHistorialById(@PathVariable Long id) {
        var historial = historialService.findById(id);
        return historial != null
                ? ResponseEntity.ok(new HistorialAcademicoDTO(historial))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<HistorialAcademicoDTO> createHistorial(@RequestBody HistorialAcademico historial) {
        HistorialAcademico nuevo = historialService.save(historial);
        return ResponseEntity.ok(new HistorialAcademicoDTO(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialAcademicoDTO> updateHistorial(@PathVariable Long id, @RequestBody HistorialAcademico actualizado) {
        HistorialAcademico actualizadoResult = historialService.update(id, actualizado);
        return actualizadoResult != null
                ? ResponseEntity.ok(new HistorialAcademicoDTO(actualizadoResult))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable Long id) {
        historialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{historialId}/aprobadas/{asignaturaId}")
    public ResponseEntity<HistorialAcademicoDTO> agregarAsignaturaAprobada(@PathVariable Long historialId, @PathVariable Long asignaturaId) {
        try {
            HistorialAcademico actualizado = historialService.agregarAsignaturaAprobada(historialId, asignaturaId);
            return ResponseEntity.ok(new HistorialAcademicoDTO(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{historialId}/proceso/{asignaturaId}")
    public ResponseEntity<HistorialAcademicoDTO> agregarAsignaturaEnProceso(@PathVariable Long historialId, @PathVariable Long asignaturaId) {
        try {
            HistorialAcademico actualizado = historialService.agregarAsignaturaEnProceso(historialId, asignaturaId);
            return ResponseEntity.ok(new HistorialAcademicoDTO(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
