package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.AsignaturaDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asignaturas")
@CrossOrigin(origins = "*") // Habilita peticiones desde el frontend (si es necesario)
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    // Obtener todas las asignaturas
    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> getAllAsignaturas() {
        List<AsignaturaDTO> dtos = asignaturaService.getAllAsignaturas()
                .stream()
                .map(AsignaturaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Obtener asignatura por ID
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> getAsignaturaById(@PathVariable Long id) {
        Optional<Asignatura> asignatura = asignaturaService.getAsignaturaById(id);
        return asignatura.map(a -> ResponseEntity.ok(new AsignaturaDTO(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva asignatura
    @PostMapping
    public ResponseEntity<AsignaturaDTO> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura creada = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.ok(new AsignaturaDTO(creada));
    }

    // Actualizar asignatura
    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> updateAsignatura(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        Asignatura actualizada = asignaturaService.updateAsignatura(id, asignatura);
        return ResponseEntity.ok(new AsignaturaDTO(actualizada));
    }

    @PatchMapping("/{id}/docente")
    public ResponseEntity<AsignaturaDTO> cambiarDocente(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, Long> body) {
    
        Long nuevoDocenteId = (body != null) ? body.get("id") : null;
        Asignatura actualizada = asignaturaService.cambiarDocente(id, nuevoDocenteId);
        return ResponseEntity.ok(new AsignaturaDTO(actualizada));
    }
    

    // Eliminar asignatura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        asignaturaService.deleteAsignatura(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<AsignaturaDTO> findByNombre(@PathVariable String nombre) {
        Optional<Asignatura> asignatura = asignaturaService.findByNombre(nombre);
        return asignatura.map(a -> ResponseEntity.ok(new AsignaturaDTO(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por Docente
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<AsignaturaDTO>> findByDocenteId(@PathVariable Long docenteId) {
        List<AsignaturaDTO> dtos = asignaturaService.findByDocenteId(docenteId)
                .stream()
                .map(AsignaturaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por Materia
    @GetMapping("/materia/{materiaId}")
    public ResponseEntity<List<AsignaturaDTO>> findByMateriaId(@PathVariable Long materiaId) {
        List<AsignaturaDTO> dtos = asignaturaService.findByMateriaId(materiaId)
                .stream()
                .map(AsignaturaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
