package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.InscripcionDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping("/inscribir")
    public ResponseEntity<InscripcionDTO> inscribir(@RequestParam Long estudianteId, @RequestParam Long claseId) {
        InscripcionDTO inscripcion = inscripcionService.inscribirEstudiante(estudianteId, claseId);
        return ResponseEntity.ok(inscripcion);
    }

    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelar(@RequestParam Long estudianteId, @RequestParam Long claseId) {
        inscripcionService.cancelarInscripcion(estudianteId, claseId);
        return ResponseEntity.ok("Inscripción cancelada correctamente.");
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<InscripcionDTO>> listarInscripciones(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.listarInscripcionesDeEstudiante(id));
    }
}
