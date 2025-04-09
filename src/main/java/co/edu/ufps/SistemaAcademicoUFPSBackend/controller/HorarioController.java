package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Horario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // Obtener todos los horarios
    @GetMapping
    public ResponseEntity<List<Horario>> getAllHorarios() {
        return ResponseEntity.ok(horarioService.getAllHorarios());
    }

    // Obtener horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.getHorarioById(id);
        return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo horario
    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.createHorario(horario));
    }

    // Actualizar un horario
    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.updateHorario(id, horario));
    }

    // Eliminar un horario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
        return ResponseEntity.noContent().build();
    }
}
