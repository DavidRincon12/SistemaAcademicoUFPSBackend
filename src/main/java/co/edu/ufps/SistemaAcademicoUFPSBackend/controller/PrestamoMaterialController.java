package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PrestamoMaterial;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.PrestamoMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos-material")
@CrossOrigin(origins = "*")
public class PrestamoMaterialController {

    @Autowired
    private PrestamoMaterialService service;

    @GetMapping
    public List<PrestamoMaterial> getAll() {
        return service.getAllPrestamos();
    }

    @GetMapping("/{id}")
    public PrestamoMaterial getById(@PathVariable Long id) {
        return service.getPrestamoById(id);
    }

    @GetMapping("/estado/{estado}")
    public List<PrestamoMaterial> getByEstado(@PathVariable String estado) {
        return service.getPrestamosByEstado(estado);
    }

    @GetMapping("/recurso/{recursoId}")
    public List<PrestamoMaterial> getByRecurso(@PathVariable Long recursoId) {
        return service.getPrestamosByRecurso(recursoId);
    }

    @PostMapping
    public PrestamoMaterial crear(@RequestBody PrestamoMaterial prestamo) {
        return service.crearPrestamo(prestamo);
    }

    @PutMapping("/{id}/devolver")
    public PrestamoMaterial devolver(@PathVariable Long id) {
        return service.devolverPrestamo(id);
    }

    @PutMapping("/{id}/mantenimiento")
    public PrestamoMaterial reportarMantenimiento(@PathVariable Long id) {
        return service.reportarMantenimiento(id);
    }
}
