package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.HistorialAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historiales")
public class HistorialAcademicoController {

    @Autowired
    private HistorialAcademicoService historialAcademicoService;

    // Obtener todos los historiales académicos
    @GetMapping
    public List<HistorialAcademico> getAllHistoriales() {
        return historialAcademicoService.getAllHistoriales();
    }

    // Obtener un historial académico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<HistorialAcademico> getHistorialById(@PathVariable Long id) {
        Optional<HistorialAcademico> historial = historialAcademicoService.getHistorialById(id);
        return historial.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener el historial académico de un estudiante por su ID
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<HistorialAcademico> getHistorialByEstudianteId(@PathVariable Long estudianteId) {
        Optional<HistorialAcademico> historial = historialAcademicoService.getHistorialByEstudianteId(estudianteId);
        return historial.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo historial académico
    @PostMapping
    public ResponseEntity<HistorialAcademico> createHistorial(@RequestBody @Valid HistorialAcademico historial) {
        HistorialAcademico nuevoHistorial = historialAcademicoService.createHistorial(historial);
        return ResponseEntity.ok(nuevoHistorial);
    }

    // Actualizar un historial académico
    @PutMapping("/{id}")
    public ResponseEntity<HistorialAcademico> updateHistorial(@PathVariable Long id,
                                                              @RequestBody HistorialAcademico historialDetails) {
        try {
            HistorialAcademico actualizado = historialAcademicoService.updateHistorial(id, historialDetails);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un historial académico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable Long id) {
        try {
            historialAcademicoService.deleteHistorial(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener el promedio ponderado de un estudiante
    @GetMapping("/estudiante/{estudianteId}/promedio")
    public ResponseEntity<Float> getPromedioPonderadoByEstudiante(@PathVariable Long estudianteId) {
        Float promedio = historialAcademicoService.getPromedioPonderadoByEstudianteId(estudianteId);
        return ResponseEntity.ok(promedio);
    }

    // Endpoint para contar cuántas materias ha aprobado un estudiante
    @GetMapping("/estudiante/{estudianteId}/aprobadas")
    public ResponseEntity<Integer> countMateriasAprobadas(@PathVariable Long estudianteId) {
        int cantidad = historialAcademicoService.countMateriasAprobadasByEstudianteId(estudianteId);
        return ResponseEntity.ok(cantidad);
    }

    // Endpoint para contar cuántas materias está cursando actualmente un estudiante
    @GetMapping("/estudiante/{estudianteId}/proceso")
    public ResponseEntity<Integer> countMateriasProceso(@PathVariable Long estudianteId) {
        int cantidad = historialAcademicoService.countMateriasProcesoByEstudianteId(estudianteId);
        return ResponseEntity.ok(cantidad);
    }

    // Endpoint para ejecutar la lógica de negocio pendiente: Calcular el promedio ponderado
    @PostMapping("/{id}/calcular")
    public ResponseEntity<Void> calcularPonderado(@PathVariable Long id) {
        try {
            historialAcademicoService.calcularPonderado(id);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // 501 Not Implemented
        }
    }
}
