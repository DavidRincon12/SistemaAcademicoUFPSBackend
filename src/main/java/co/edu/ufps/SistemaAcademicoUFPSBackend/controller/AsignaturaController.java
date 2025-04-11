package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas")
@CrossOrigin(origins = "*") // Habilita peticiones desde el frontend (si es necesario)
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    // Obtener todas las asignaturas
    @GetMapping
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        return ResponseEntity.ok(asignaturaService.getAllAsignaturas());
    }

    // Obtener asignatura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Long id) {
        Optional<Asignatura> asignatura = asignaturaService.getAsignaturaById(id);
        return asignatura.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva asignatura
    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        return ResponseEntity.ok(asignaturaService.createAsignatura(asignatura));
    }

    // Actualizar asignatura
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        return ResponseEntity.ok(asignaturaService.updateAsignatura(id, asignatura));
    }

    // Eliminar asignatura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        asignaturaService.deleteAsignatura(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Asignatura> findByNombre(@PathVariable String nombre) {
        Optional<Asignatura> asignatura = asignaturaService.findByNombre(nombre);
        return asignatura.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Buscar por Docente
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<Asignatura>> findByDocenteId(@PathVariable Long docenteId) {
        return ResponseEntity.ok(asignaturaService.findByDocenteId(docenteId));
    }

    // Buscar por Materia
    @GetMapping("/materia/{materiaId}")
    public ResponseEntity<List<Asignatura>> findByMateriaId(@PathVariable Long materiaId) {
        return ResponseEntity.ok(asignaturaService.findByMateriaId(materiaId));
    }

    // Buscar por Estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Asignatura>> findByEstudianteId(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(asignaturaService.findByEstudianteId(estudianteId));
    }

    // Buscar habilitadas
    @GetMapping("/habilitacion")
    public ResponseEntity<List<Asignatura>> findByHabilitacion() {
        return ResponseEntity.ok(asignaturaService.findByHabilitacion());
    }

    // Buscar vacacionales
    @GetMapping("/vacacional")
    public ResponseEntity<List<Asignatura>> findByVacacional() {
        return ResponseEntity.ok(asignaturaService.findByVacacional());
    }
}
