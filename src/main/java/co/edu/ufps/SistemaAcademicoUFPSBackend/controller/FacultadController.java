package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.FacultadDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/facultades")
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    // Obtener todas las facultades
    @GetMapping
    public List<FacultadDTO> getAllFacultades() {
        return facultadService.getAllFacultades()
                .stream()
                .map(FacultadDTO::new)
                .toList();
    }

    // Obtener una facultad por ID
    @GetMapping("/{id}")
    public ResponseEntity<FacultadDTO> getFacultadById(@PathVariable Long id) {
        Optional<Facultad> facultad = facultadService.getFacultadById(id);
        return facultad.map(f -> ResponseEntity.ok(new FacultadDTO(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva facultad
    @PostMapping
    public ResponseEntity<Facultad> createFacultad(@RequestBody @Valid Facultad facultad) {
        return ResponseEntity.ok(facultadService.createFacultad(facultad));
    }

    // Actualizar una facultad existente
    @PutMapping("/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable Long id, @RequestBody Facultad facultadDetails) {
        try {
            Facultad updatedFacultad = facultadService.updateFacultad(id, facultadDetails);
            return ResponseEntity.ok(updatedFacultad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una facultad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable Long id) {
        try {
            facultadService.deleteFacultad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
