package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.EstudianteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Obtener todos los estudiantes
    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.getEstudianteById(id);
        return estudiante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody @Valid Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.createEstudiante(estudiante));
    }

    // Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(
            @PathVariable Long id,
            @RequestParam LocalDate fechaInscripcion,
            @RequestParam String estado,
            @RequestParam String becas,
            @RequestParam String correoEstudiantil,
            @RequestParam short creditosAprobados) {
        try {
            Estudiante estudiante = estudianteService.updateEstudiante(id, fechaInscripcion, estado, becas, correoEstudiantil, creditosAprobados);
            return ResponseEntity.ok(estudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        try {
            estudianteService.deleteEstudiante(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar estudiante por correo institucional
    @GetMapping("/correo")
    public ResponseEntity<Estudiante> getEstudianteByCorreo(@RequestParam String correo) {
        Optional<Estudiante> estudiante = estudianteService.getEstudianteByCorreo(correo);
        return estudiante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener estudiantes por estado
    @GetMapping("/estado")
    public List<Estudiante> getEstudiantesByEstado(@RequestParam String estado) {
        return estudianteService.getEstudiantesByEstado(estado);
    }

    // Obtener estudiantes con becas
    @GetMapping("/becas")
    public List<Estudiante> getEstudiantesConBecas() {
        return estudianteService.getEstudiantesConBecas();
    }

    // Obtener estudiantes por programa académico
    @GetMapping("/programa/{programaId}")
    public List<Estudiante> getEstudiantesByPrograma(@PathVariable Long programaId) {
        return estudianteService.getEstudiantesByPrograma(programaId);
    }

    // Buscar estudiantes por nombre
    @GetMapping("/buscar")
    public List<Estudiante> searchEstudiantesByNombre(@RequestParam String nombre) {
        return estudianteService.searchEstudiantesByNombre(nombre);
    }
}