package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HorarioAsesoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios-asesoria")
public class HorarioAsesoriaController {

    @Autowired
    private HorarioAsesoriaService horarioAsesoriaService;

    // Obtener horarios de asesoría disponibles para un día y hora específicos
    @GetMapping("/disponibles")
    public ResponseEntity<List<HorarioAsesoria>> getHorariosDisponibles(
            @RequestParam DayOfWeek diaSemana,
            @RequestParam LocalTime hora) {
        List<HorarioAsesoria> horarios = horarioAsesoriaService.getHorariosDisponibles(diaSemana, hora);
        if (horarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horarios);
    }

    // Obtener todos los horarios de asesoría
    @GetMapping
    public List<HorarioAsesoria> getAllHorariosAsesoria() {
        return horarioAsesoriaService.getAllHorariosAsesoria();
    }

    // Obtener un horario de asesoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<HorarioAsesoria> getHorarioAsesoriaById(@PathVariable Long id) {
        Optional<HorarioAsesoria> horarioAsesoria = horarioAsesoriaService.getHorarioAsesoriaById(id);
        return horarioAsesoria.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo horario de asesoría
@PostMapping
public ResponseEntity<HorarioAsesoria> createHorario(@RequestBody @Valid HorarioAsesoria horario) {
    return ResponseEntity.ok(horarioAsesoriaService.createHorarioAsesoria(horario));
}


    // Actualizar un horario de asesoría existente
    @PutMapping("/{id}")
    public ResponseEntity<HorarioAsesoria> updateHorarioAsesoria(@PathVariable Long id,
            @RequestBody HorarioAsesoria horarioAsesoriaDetails) {
        try {
            HorarioAsesoria updatedHorarioAsesoria = horarioAsesoriaService.updateHorarioAsesoria(id,
                    horarioAsesoriaDetails);
            return ResponseEntity.ok(updatedHorarioAsesoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un horario de asesoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorarioAsesoria(@PathVariable Long id) {
        try {
            horarioAsesoriaService.deleteHorarioAsesoria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}