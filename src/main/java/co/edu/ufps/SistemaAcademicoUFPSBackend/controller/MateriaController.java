package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias
    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    // Obtener una materia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Long id) {
        Optional<Materia> materia = materiaService.getMateriaById(id);
        return materia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<Materia> createMateria(@RequestBody @Valid Materia materia) {
        return ResponseEntity.ok(materiaService.createMateria(materia));
    }

    // Actualizar una materia
    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable Long id, @RequestBody Materia materiaDetails) {
        try {
            Materia updatedMateria = materiaService.updateMateria(id, materiaDetails);
            return ResponseEntity.ok(updatedMateria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una materia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        try {
            materiaService.deleteMateria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar materia por nombre
    @GetMapping("/nombre")
    public ResponseEntity<Materia> getMateriaByNombre(@RequestParam String nombre) {
        Optional<Materia> materia = materiaService.getMateriaByNombre(nombre);
        return materia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todas las materias de un programa
    @GetMapping("/programa/{programaId}")
    public List<Materia> getMateriasByPrograma(@PathVariable Long programaId) {
        return materiaService.getMateriasByProgramaId(programaId);
    }
}
