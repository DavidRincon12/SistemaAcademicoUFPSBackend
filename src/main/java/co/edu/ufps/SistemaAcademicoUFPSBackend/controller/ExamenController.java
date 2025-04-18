package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ExamenDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Examen;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping
    public List<ExamenDTO> getAllExamenes() {
        return examenService.getAllExamenes().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenDTO> getExamenById(@PathVariable Long id) {
        Optional<Examen> examen = examenService.getExamenById(id);
        return examen.map(e -> ResponseEntity.ok(convertToDTO(e))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExamenDTO> createExamen(@RequestBody @Valid Examen examen) {
        Examen nuevo = examenService.createExamen(examen);
        return ResponseEntity.ok(convertToDTO(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamenDTO> updateExamen(@PathVariable Long id, @RequestBody @Valid Examen examenDetails) {
        Examen actualizado = examenService.updateExamen(id, examenDetails);
        return ResponseEntity.ok(convertToDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return ResponseEntity.noContent().build();
    }

    private ExamenDTO convertToDTO(Examen examen) {
        ExamenDTO dto = new ExamenDTO();
        dto.setId(examen.getId());
        dto.setTipo(examen.getTipo());

        if (examen.getClase() != null) {
            dto.setClaseId(examen.getClase().getId());
            dto.setClaseNombre(examen.getClase().getNombre());
        }

        return dto;
    }
}
