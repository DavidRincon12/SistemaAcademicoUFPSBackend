package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/semestres")
@CrossOrigin(origins = "*")
public class SemestreController {

    @Autowired
    private SemestreService semestreService;

    // Obtener todos los semestres
    @GetMapping
    public ResponseEntity<List<Semestre>> getAllSemestres() {
        return ResponseEntity.ok(semestreService.getAllSemestres());
    }

    // Obtener semestre por ID
    @GetMapping("/{id}")
    public ResponseEntity<Semestre> getSemestreById(@PathVariable Long id) {
        Optional<Semestre> semestre = semestreService.getSemestreById(id);
        return semestre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener semestre por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Semestre> getSemestreByNombre(@PathVariable String nombre) {
        Optional<Semestre> semestre = semestreService.getSemestreByNombre(nombre);
        return semestre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener el semestre actual
    @GetMapping("/actual")
    public ResponseEntity<Semestre> getSemestreActual() {
        Optional<Semestre> semestre = semestreService.getSemestreActual();
        return semestre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo semestre
    @PostMapping
    public ResponseEntity<Semestre> createSemestre(@RequestBody Semestre semestre) {
        return ResponseEntity.ok(semestreService.createSemestre(semestre));
    }

    // Actualizar semestre
    @PutMapping("/{id}")
    public ResponseEntity<Semestre> updateSemestre(@PathVariable Long id, @RequestBody Semestre semestre) {
        return ResponseEntity.ok(semestreService.updateSemestre(id, semestre));
    }

    // Eliminar semestre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemestre(@PathVariable Long id) {
        semestreService.deleteSemestre(id);
        return ResponseEntity.noContent().build();
    }

    // Validar si el semestre es el actual (NO IMPLEMENTADO)
    @GetMapping("/{id}/validar-actual")
    public ResponseEntity<?> validarPeriodoActual(@PathVariable Long id) {
        return ResponseEntity.status(501).body("Método no implementado");
    }

    // Asignar calendario académico (NO IMPLEMENTADO - rel. corregida)
    @PostMapping("/{id}/asignar-calendario/{calendarioId}")
    public ResponseEntity<?> asignarCalendario(@PathVariable Long id, @PathVariable Long calendarioId) {
        return ResponseEntity.status(501).body("Método no implementado");
    }
}
