package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.AsistenciaDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoAsistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public List<AsistenciaDTO> getAllAsistencias() {
        return asistenciaService.getAllAsistencias().stream()
                .map(AsistenciaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> getAsistenciaById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.getAsistenciaById(id);
        return asistencia.map(a -> ResponseEntity.ok(new AsistenciaDTO(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AsistenciaDTO> createAsistencia(@RequestBody @Valid Asistencia asistencia) {
        Asistencia nueva = asistenciaService.createAsistencia(asistencia);
        return ResponseEntity.ok(new AsistenciaDTO(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        try {
            Asistencia actualizada = asistenciaService.updateAsistencia(id, asistenciaDetails);
            return ResponseEntity.ok(new AsistenciaDTO(actualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        try {
            asistenciaService.deleteAsistencia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estudiante/{estudianteId}")
    public List<AsistenciaDTO> getAsistenciasByEstudiante(@PathVariable Long estudianteId) {
        return asistenciaService.findByEstudianteId(estudianteId).stream()
                .map(AsistenciaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/clase/{claseId}")
    public List<AsistenciaDTO> getAsistenciasByClase(@PathVariable Long claseId) {
        return asistenciaService.findByClaseId(claseId).stream()
                .map(AsistenciaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/estudiante/{estudianteId}/clase/{claseId}")
    public List<AsistenciaDTO> getAsistenciasByEstudianteAndClase(@PathVariable Long estudianteId,
                                                                  @PathVariable Long claseId) {
        return asistenciaService.findByEstudianteIdAndClaseId(estudianteId, claseId).stream()
                .map(AsistenciaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/estado/{estado}")
    public List<AsistenciaDTO> getAsistenciasByEstado(@PathVariable EstadoAsistencia estado) {
        return asistenciaService.findByEstado(estado).stream()
                .map(AsistenciaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/clase/{claseId}/estado/{estado}")
    public List<AsistenciaDTO> getAsistenciasByClaseAndEstado(@PathVariable Long claseId,
                                                              @PathVariable EstadoAsistencia estado) {
        return asistenciaService.findByClaseIdAndEstado(claseId, estado).stream()
                .map(AsistenciaDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarAsistencia() {
        try {
            asistenciaService.registrarAsistencia();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Void> consultarAsistencia() {
        try {
            asistenciaService.consultarAsistencia();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
