package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Examen;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping
    public List<Examen> getAllExamenes() {
        return examenService.getAllExamenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        Optional<Examen> examen = examenService.getExamenById(id);
        return examen.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Examen> createExamen(@RequestBody @Valid Examen examen) {
        Examen nuevo = examenService.createExamen(examen);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable Long id, @RequestBody @Valid Examen examenDetails) {
        Examen actualizado = examenService.updateExamen(id, examenDetails);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return ResponseEntity.noContent().build();
    }
}