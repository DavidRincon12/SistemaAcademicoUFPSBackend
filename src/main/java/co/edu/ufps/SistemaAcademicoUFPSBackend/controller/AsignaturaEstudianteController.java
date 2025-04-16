package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.AsignaturaEstudianteDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.AsignaturaEstudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsignaturaEstudianteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asignaturaestudiante")
public class AsignaturaEstudianteController {

    @Autowired
    private AsignaturaEstudianteService asignaturaEstudianteService;

    // Obtener todas las relaciones
    @GetMapping
    public List<AsignaturaEstudianteDTO> getAll() {
        return asignaturaEstudianteService.getAllAsignaturasEstudiantes()
                .stream()
                .map(AsignaturaEstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener una relación por ID
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaEstudianteDTO> getById(@PathVariable Long id) {
        Optional<AsignaturaEstudiante> ae = asignaturaEstudianteService.getById(id);
        return ae.map(asignaturaEstudiante -> ResponseEntity.ok(new AsignaturaEstudianteDTO(asignaturaEstudiante)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva relación
    @PostMapping
    public ResponseEntity<AsignaturaEstudianteDTO> create(@RequestBody @Valid AsignaturaEstudiante asignaturaEstudiante) {
        try {
            AsignaturaEstudiante creada = asignaturaEstudianteService.create(asignaturaEstudiante);
            return ResponseEntity.ok(new AsignaturaEstudianteDTO(creada));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Actualizar una relación existente
    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaEstudianteDTO> update(@PathVariable Long id,
                                                          @RequestBody @Valid AsignaturaEstudiante detalles) {
        try {
            AsignaturaEstudiante actualizada = asignaturaEstudianteService.update(id, detalles);
            return ResponseEntity.ok(new AsignaturaEstudianteDTO(actualizada));
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
    public List<AsignaturaEstudianteDTO> getByAsignatura(@PathVariable Long asignaturaId) {
        return asignaturaEstudianteService.getByAsignaturaId(asignaturaId)
                .stream()
                .map(AsignaturaEstudianteDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener todas las relaciones por estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public List<AsignaturaEstudianteDTO> getByEstudiante(@PathVariable Long estudianteId) {
        return asignaturaEstudianteService.getByEstudianteId(estudianteId)
                .stream()
                .map(AsignaturaEstudianteDTO::new)
                .collect(Collectors.toList());
    }
}
