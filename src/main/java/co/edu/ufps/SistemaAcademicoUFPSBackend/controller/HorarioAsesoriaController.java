package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HorarioAsesoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios-asesoria")
@CrossOrigin(origins = "*")
public class HorarioAsesoriaController {

    @Autowired
    private HorarioAsesoriaService horarioAsesoriaService;

    @GetMapping
    public List<HorarioAsesoria> getAllHorariosAsesoria() {
        return horarioAsesoriaService.getAllHorariosAsesoria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioAsesoria> getHorarioAsesoriaById(@PathVariable Long id) {
        Optional<HorarioAsesoria> horario = horarioAsesoriaService.getHorarioAsesoriaById(id);
        return horario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HorarioAsesoria> createHorario(@RequestBody @Valid HorarioAsesoria horario) {
        HorarioAsesoria creado = horarioAsesoriaService.createHorarioAsesoria(horario);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioAsesoria> updateHorario(@PathVariable Long id, @RequestBody HorarioAsesoria horario) {
        HorarioAsesoria actualizado = horarioAsesoriaService.updateHorarioAsesoria(id, horario);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioAsesoriaService.deleteHorarioAsesoria(id);
        return ResponseEntity.noContent().build();
    }
}
