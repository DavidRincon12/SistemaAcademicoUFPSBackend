package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ClaseDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clases")
@CrossOrigin(origins = "*")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    // Obtener todas las clases
    @GetMapping
    public List<ClaseDTO> getAllClases() {
        return claseService.getAllClases()
                .stream()
                .map(ClaseDTO::new)
                .collect(Collectors.toList());
    }

    // Obtener una clase por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClaseDTO> getClaseById(@PathVariable Long id) {
        Optional<Clase> clase = claseService.getClaseById(id);
        return clase.map(c -> ResponseEntity.ok(new ClaseDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva clase
    @PostMapping
    public ResponseEntity<ClaseDTO> createClase(@RequestBody @Valid Clase clase) {
        Clase nuevaClase = claseService.createClase(clase);
        return ResponseEntity.ok(new ClaseDTO(nuevaClase));
    }

    // Actualizar una clase
    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTO> updateClase(@PathVariable Long id, @RequestBody Clase claseDetails) {
        try {
            Clase updatedClase = claseService.updateClase(id, claseDetails);
            return ResponseEntity.ok(new ClaseDTO(updatedClase));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una clase
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        try {
            claseService.deleteClase(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar clases por asignatura
    @GetMapping("/asignatura/{asignaturaId}")
    public List<ClaseDTO> getClasesByAsignatura(@PathVariable Long asignaturaId) {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(asignaturaId);
        return claseService.findByAsignatura(asignatura)
                .stream().map(ClaseDTO::new).collect(Collectors.toList());
    }

    // Buscar clases por docente
    @GetMapping("/docente/{docenteId}")
    public List<ClaseDTO> getClasesByDocente(@PathVariable Long docenteId) {
        Docente docente = new Docente();
        docente.setId(docenteId);
        return claseService.findByDocente(docente)
                .stream().map(ClaseDTO::new).collect(Collectors.toList());
    }

    // Buscar clases por semestre
    @GetMapping("/semestre/{semestreId}")
    public List<ClaseDTO> getClasesBySemestre(@PathVariable Long semestreId) {
        Semestre semestre = new Semestre();
        semestre.setId(semestreId);
        return claseService.findBySemestre(semestre)
                .stream().map(ClaseDTO::new).collect(Collectors.toList());
    }

    // Buscar clases por fecha
    @GetMapping("/fecha")
    public List<ClaseDTO> getClasesByFecha(@RequestParam Date fecha) {
        return claseService.findByFecha(fecha)
                .stream().map(ClaseDTO::new).collect(Collectors.toList());
    }

    // Buscar una clase por nombre
    @GetMapping("/nombre")
    public ResponseEntity<ClaseDTO> getClaseByNombre(@RequestParam String nombre) {
        Optional<Clase> clase = claseService.findByNombre(nombre);
        return clase.map(c -> ResponseEntity.ok(new ClaseDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Consultar el conteo de asistencias de una clase
    @GetMapping("/{id}/asistencias")
    public ResponseEntity<Integer> contarAsistenciasPorClase(@PathVariable Long id) {
        int cantidad = claseService.contarAsistenciasPorClase(id);
        return ResponseEntity.ok(cantidad);
    }

    // Obtener clases con cupo disponible
    @GetMapping("/cupoDisponible")
    public List<ClaseDTO> getClasesConCupoDisponible() {
        return claseService.findClasesConCupoDisponible()
                .stream().map(ClaseDTO::new).collect(Collectors.toList());
    }

    // Métodos adicionales de negocio (aún no implementados)

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<Void> iniciarClase(@PathVariable Long id) {
        try {
            claseService.iniciarClase();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarClase(@PathVariable Long id) {
        try {
            claseService.finalizarClase();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    @PostMapping("/{id}/registrarAsistencia")
    public ResponseEntity<Void> registrarAsistencia(@PathVariable Long id) {
        try {
            claseService.registrarAsistencia();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    @GetMapping("/{id}/reporte")
    public ResponseEntity<Void> obtenerReporte(@PathVariable Long id) {
        try {
            claseService.obtenerReporte();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
