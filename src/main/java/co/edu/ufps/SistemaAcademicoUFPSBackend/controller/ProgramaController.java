package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programas")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    // Obtener todos los programas
    @GetMapping
    public List<Programa> getAllProgramas() {
        return programaService.getAllProgramas();
    }

    // Obtener un programa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Programa> getProgramaById(@PathVariable Long id) {
        Optional<Programa> programa = programaService.getProgramaById(id);
        return programa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo programa
    @PostMapping
    public Programa createPrograma(@RequestBody Programa programa) {
        return programaService.createPrograma(programa);
    }

    // Actualizar un programa existente
    @PutMapping("/{id}")
    public ResponseEntity<Programa> updatePrograma(@PathVariable Long id, @RequestBody Programa programaDetails) {
        try {
            Programa updatedPrograma = programaService.updatePrograma(id, programaDetails);
            return ResponseEntity.ok(updatedPrograma);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un programa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrograma(@PathVariable Long id) {
        try {
            programaService.deletePrograma(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}