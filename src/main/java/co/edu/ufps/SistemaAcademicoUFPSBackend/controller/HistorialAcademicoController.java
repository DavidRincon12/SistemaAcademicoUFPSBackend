package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.HistorialAcademicoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.InformeAcademicoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HistorialAcademicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historiales")
public class HistorialAcademicoController {

    private final HistorialAcademicoService historialService;

    public HistorialAcademicoController(HistorialAcademicoService historialService) {
        this.historialService = historialService;
    }

    @GetMapping
    public List<HistorialAcademicoDTO> getAll() {
        return historialService.findAll()
                .stream()
                .map(HistorialAcademicoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialAcademicoDTO> getById(@PathVariable Long id) {
        try {
            HistorialAcademico historial = historialService.findById(id);
            return ResponseEntity.ok(new HistorialAcademicoDTO(historial));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<HistorialAcademicoDTO> create(@RequestBody HistorialAcademico historial) {
        HistorialAcademico saved = historialService.save(historial);
        return ResponseEntity.ok(new HistorialAcademicoDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        historialService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/informe/estudiante/{id}")
    public ResponseEntity<InformeAcademicoDTO> getInforme(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(historialService.generarInformePorEstudiante(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/informe/estudiante/{id}/pdf")
    public ResponseEntity<byte[]> descargarInformePDF(@PathVariable Long id) {
        byte[] pdfBytes = historialService.generarInformePDF(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=informe_estudiante_" + id + ".pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
