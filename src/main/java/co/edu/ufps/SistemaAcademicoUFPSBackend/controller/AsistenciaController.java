package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // Obtener todas las asistencias
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaService.getAllAsistencias();
    }

    // Obtener una asistencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.getAsistenciaById(id);
        return asistencia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva asistencia
    @PostMapping
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody @Valid Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.createAsistencia(asistencia);
        return ResponseEntity.ok(nuevaAsistencia);
    }

    // Actualizar una asistencia
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        try {
            Asistencia updatedAsistencia = asistenciaService.updateAsistencia(id, asistenciaDetails);
            return ResponseEntity.ok(updatedAsistencia);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una asistencia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        try {
            asistenciaService.deleteAsistencia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar asistencias por ID de estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public List<Asistencia> getAsistenciasByEstudiante(@PathVariable Long estudianteId) {
        return asistenciaService.findByEstudianteId(estudianteId);
    }

    // Buscar asistencias por ID de clase
    @GetMapping("/clase/{claseId}")
    public List<Asistencia> getAsistenciasByClase(@PathVariable Long claseId) {
        return asistenciaService.findByClaseId(claseId);
    }

    // Buscar asistencias por fecha específica
    @GetMapping("/fecha")
    public List<Asistencia> getAsistenciasByFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return asistenciaService.findByFecha(fecha);
    }

    // Buscar asistencias de un estudiante en una clase específica
    @GetMapping("/estudiante/{estudianteId}/clase/{claseId}")
    public List<Asistencia> getAsistenciasByEstudianteAndClase(@PathVariable Long estudianteId,
                                                               @PathVariable Long claseId) {
        return asistenciaService.findByEstudianteIdAndClaseId(estudianteId, claseId);
    }

    // Buscar asistencias de un estudiante en un rango de fechas
    @GetMapping("/estudiante/{estudianteId}/rango")
    public List<Asistencia> getAsistenciasByEstudianteAndFechaBetween(
            @PathVariable Long estudianteId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        return asistenciaService.findByEstudianteIdAndFechaBetween(estudianteId, fechaInicio, fechaFin);
    }

    // Buscar si un estudiante asistió a una clase en una fecha específica
    @GetMapping("/estudiante/{estudianteId}/clase/{claseId}/fecha")
    public ResponseEntity<Asistencia> getAsistenciaByEstudianteAndClaseAndFecha(
            @PathVariable Long estudianteId,
            @RequestParam Long claseId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        Optional<Asistencia> asistencia = asistenciaService.findByEstudianteIdAndClaseIdAndFecha(estudianteId, claseId, fecha);
        return asistencia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Métodos de negocio pendientes
    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarAsistencia() {
        try {
            asistenciaService.registrarAsistencia();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Void> consultarAsistencia() {
        try {
            asistenciaService.consultarAsistencia();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }
}
