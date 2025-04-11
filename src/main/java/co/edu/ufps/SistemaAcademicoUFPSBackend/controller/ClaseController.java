package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
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

@RestController
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    // Obtener todas las clases
    @GetMapping
    public List<Clase> getAllClases() {
        return claseService.getAllClases();
    }

    // Obtener una clase por ID
    @GetMapping("/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id) {
        Optional<Clase> clase = claseService.getClaseById(id);
        return clase.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva clase
    @PostMapping
    public ResponseEntity<Clase> createClase(@RequestBody @Valid Clase clase) {
        Clase nuevaClase = claseService.createClase(clase);
        return ResponseEntity.ok(nuevaClase);
    }

    // Actualizar una clase
    @PutMapping("/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Long id, @RequestBody Clase claseDetails) {
        try {
            Clase updatedClase = claseService.updateClase(id, claseDetails);
            return ResponseEntity.ok(updatedClase);
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
    // Se envía el id de la asignatura y se construye el objeto Asignatura de forma básica.
    @GetMapping("/asignatura/{asignaturaId}")
    public List<Clase> getClasesByAsignatura(@PathVariable Long asignaturaId) {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(asignaturaId);
        return claseService.findByAsignatura(asignatura);
    }

    // Buscar clases por docente
    @GetMapping("/docente/{docenteId}")
    public List<Clase> getClasesByDocente(@PathVariable Long docenteId) {
        Docente docente = new Docente();
        docente.setId(docenteId);
        return claseService.findByDocente(docente);
    }

    // Buscar clases por semestre
    @GetMapping("/semestre/{semestreId}")
    public List<Clase> getClasesBySemestre(@PathVariable Long semestreId) {
        Semestre semestre = new Semestre();
        semestre.setId(semestreId);
        return claseService.findBySemestre(semestre);
    }

    // Buscar clases por fecha
    @GetMapping("/fecha")
    public List<Clase> getClasesByFecha(@RequestParam Date fecha) {
        return claseService.findByFecha(fecha);
    }

    // Buscar una clase por nombre
    @GetMapping("/nombre")
    public ResponseEntity<Clase> getClaseByNombre(@RequestParam String nombre) {
        Optional<Clase> clase = claseService.findByNombre(nombre);
        return clase.map(ResponseEntity::ok)
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
    public List<Clase> getClasesConCupoDisponible() {
        return claseService.findClasesConCupoDisponible();
    }

    // Métodos de negocio adicionales (iniciar, finalizar, obtener reporte, etc.)
    // Se pueden exponer endpoints que activen la lógica respectiva

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
