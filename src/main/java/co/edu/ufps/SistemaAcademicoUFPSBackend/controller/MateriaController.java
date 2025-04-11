package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.MateriaDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias
    @GetMapping
    public List<MateriaDTO> getAllMaterias() {
        return materiaService.getAllMaterias()
                .stream()
                .map(MateriaDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener una materia por ID
    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getMateriaById(@PathVariable Long id) {
        Optional<Materia> materia = materiaService.getMateriaById(id);
        return materia.map(m -> ResponseEntity.ok(new MateriaDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@RequestBody @Valid Materia materia) {
        Materia nueva = materiaService.createMateria(materia);
        return ResponseEntity.ok(new MateriaDTO(nueva));
    }

    // Actualizar una materia
    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> updateMateria(@PathVariable Long id, @RequestBody Materia materiaDetails) {
        try {
            Materia updated = materiaService.updateMateria(id, materiaDetails);
            return ResponseEntity.ok(new MateriaDTO(updated));
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
    public ResponseEntity<MateriaDTO> getMateriaByNombre(@RequestParam String nombre) {
        Optional<Materia> materia = materiaService.getMateriaByNombre(nombre);
        return materia.map(m -> ResponseEntity.ok(new MateriaDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todas las materias de un programa
    @GetMapping("/programa/{programaId}")
    public List<MateriaDTO> getMateriasByPrograma(@PathVariable Long programaId) {
        return materiaService.getMateriasByProgramaId(programaId)
                .stream()
                .map(MateriaDTO::new)
                .collect(Collectors.toList());
    }
}
