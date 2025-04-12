package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.EstudianteDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.EstudianteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Obtener todos los estudiantes
    @GetMapping
    public List<EstudianteDTO> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes()
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.getEstudianteById(id);
        return estudiante.map(e -> ResponseEntity.ok(new EstudianteDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<EstudianteDTO> createEstudiante(@RequestBody @Valid Estudiante estudiante) {
        Estudiante creado = estudianteService.createEstudiante(estudiante);
        return ResponseEntity.ok(new EstudianteDTO(creado));
    }

    // Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(
            @PathVariable Long id,
            @RequestBody @Valid Estudiante estudiante) {
        try {
            Estudiante actualizado = estudianteService.updateEstudiante(id, estudiante);
            return ResponseEntity.ok(new EstudianteDTO(actualizado));
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
    public ResponseEntity<EstudianteDTO> getEstudianteByCorreo(@RequestParam String correo) {
        Optional<Estudiante> estudiante = estudianteService.getEstudianteByCorreo(correo);
        return estudiante.map(e -> ResponseEntity.ok(new EstudianteDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener estudiantes por estado
    @GetMapping("/estado")
    public List<EstudianteDTO> getEstudiantesByEstado(@RequestParam String estado) {
        return estudianteService.getEstudiantesByEstado(estado)
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener estudiantes con becas
    @GetMapping("/becas")
    public List<EstudianteDTO> getEstudiantesConBecas() {
        return estudianteService.getEstudiantesConBecas()
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener estudiantes por programa académico
    @GetMapping("/programa/{programaId}")
    public List<EstudianteDTO> getEstudiantesByPrograma(@PathVariable Long programaId) {
        return estudianteService.getEstudiantesByPrograma(programaId)
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar estudiantes por nombre
    @GetMapping("/buscar")
    public List<EstudianteDTO> searchEstudiantesByNombre(@RequestParam String nombre) {
        return estudianteService.searchEstudiantesByNombre(nombre)
                .stream()
                .map(EstudianteDTO::new)
                .collect(Collectors.toList());
    }
}
