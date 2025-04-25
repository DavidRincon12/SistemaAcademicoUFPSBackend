package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ReservaRecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas-recurso")
@CrossOrigin(origins = "*")
public class ReservaRecursoController {

    @Autowired
    private ReservaRecursoService reservaRecursoService;

    // Obtener todas las reservas
    @GetMapping
    public List<ReservaRecurso> getAllReservas() {
        return reservaRecursoService.getAllReservas();
    }

    // Obtener reserva por ID
    @GetMapping("/{id}")
    public Optional<ReservaRecurso> getReservaById(@PathVariable Long id) {
        return reservaRecursoService.getReservaById(id);
    }

    // Obtener reservas por estado
    @GetMapping("/estado/{estado}")
    public List<ReservaRecurso> getReservasByEstado(@PathVariable String estado) {
        return reservaRecursoService.getReservasByEstado(estado);
    }

    // Obtener reservas por nombre
    @GetMapping("/nombre/{nombre}")
    public List<ReservaRecurso> getReservasByNombre(@PathVariable String nombre) {
        return reservaRecursoService.getReservasByNombre(nombre);
    }

    @GetMapping("/activas")
    public List<ReservaRecurso> getReservasActivas(
            @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss") Date inicio,
            @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss") Date fin
    ) {
        return reservaRecursoService.getReservasActivas(inicio, fin);
    }

    // Crear una nueva reserva
    @PostMapping
    public ReservaRecurso createReserva(@RequestBody ReservaRecurso reserva) {
        return reservaRecursoService.createReserva(reserva);
    }

    // Actualizar una reserva
    @PutMapping("/{id}")
    public ReservaRecurso updateReserva(@PathVariable Long id, @RequestBody ReservaRecurso reservaDetails) {
        return reservaRecursoService.updateReserva(id, reservaDetails);
    }

    // Aprobar una reserva
    @PutMapping("/{id}/aprobar")
    public ReservaRecurso aprobarReserva(@PathVariable Long id) {
        return reservaRecursoService.aprobarReserva(id);
    }

    // Eliminar una reserva
    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Long id) {
        reservaRecursoService.deleteReserva(id);
    }

    // Extender reserva (no implementado aún)
    @PutMapping("/{id}/extender")
    public ReservaRecurso extenderReserva(@PathVariable Long id, @RequestParam Date nuevaFechaFin) {
        return reservaRecursoService.extenderReserva(id, nuevaFechaFin);
    }
}
