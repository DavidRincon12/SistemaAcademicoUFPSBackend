package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.AsignaturaEstudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsignaturaEstudianteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asignaturaestudiante")
public class AsignaturaEstudianteController {

    @Autowired
    private AsignaturaEstudianteService asignaturaEstudianteService;

    // Obtener todas las relaciones
    @GetMapping
    public List<AsignaturaEstudiante> getAll() {
        return asignaturaEstudianteService.getAllAsignaturasEstudiantes();
    }

    // Obtener una relación por ID
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaEstudiante> getById(@PathVariable Long id) {
        Optional<AsignaturaEstudiante> ae = asignaturaEstudianteService.getById(id);
        return ae.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva relación
    @PostMapping
    public ResponseEntity<AsignaturaEstudiante> create(@RequestBody @Valid AsignaturaEstudiante asignaturaEstudiante) {
        try {
            AsignaturaEstudiante creada = asignaturaEstudianteService.create(asignaturaEstudiante);
            return ResponseEntity.ok(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Actualizar una relación existente
    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaEstudiante> update(@PathVariable Long id,
                                                       @RequestBody @Valid AsignaturaEstudiante detalles) {
        try {
            AsignaturaEstudiante actualizada = asignaturaEstudianteService.update(id, detalles);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una relación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            asignaturaEstudianteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener todas las relaciones por asignatura
    @GetMapping("/asignatura/{asignaturaId}")
    public List<AsignaturaEstudiante> getByAsignatura(@PathVariable Long asignaturaId) {
        return asignaturaEstudianteService.getByAsignaturaId(asignaturaId);
    }

    // Obtener todas las relaciones por estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public List<AsignaturaEstudiante> getByEstudiante(@PathVariable Long estudianteId) {
        return asignaturaEstudianteService.getByEstudianteId(estudianteId);
    }
}
