package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.RecursoAcademicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recursos-academicos")
@CrossOrigin(origins = "*")
public class RecursoAcademicoController {

    @Autowired
    private RecursoAcademicoService recursoAcademicoService;

    @Autowired
    private PersonaRepository personaRepository;

    // Obtener todos los recursos académicos
    @GetMapping
    public List<RecursoAcademico> getAllRecursos() {
        return recursoAcademicoService.getAllRecursos();
    }

    // Obtener un recurso académico por ID
    @GetMapping("/{id}")
    public Optional<RecursoAcademico> getRecursoById(@PathVariable Long id) {
        return recursoAcademicoService.getRecursoById(id);
    }

    // Crear un recurso académico
    @PostMapping
    public RecursoAcademico createRecurso(@RequestBody RecursoAcademico recurso) {
        if (recurso.getResponsable() != null && recurso.getResponsable().getId() != null) {
            Persona responsable = personaRepository.findById(recurso.getResponsable().getId())
                    .orElseThrow(() -> new RuntimeException("Responsable no encontrado con ID: " + recurso.getResponsable().getId()));
            recurso.setResponsable(responsable);
        }

        return recursoAcademicoService.createRecurso(recurso);
    }

    // Actualizar un recurso académico
    @PutMapping("/{id}")
    public RecursoAcademico updateRecurso(@PathVariable Long id, @RequestBody RecursoAcademico recursoDetails) {
        return recursoAcademicoService.updateRecurso(id, recursoDetails);
    }

    // Eliminar un recurso académico
    @DeleteMapping("/{id}")
    public void deleteRecurso(@PathVariable Long id) {
        recursoAcademicoService.deleteRecurso(id);
    }

    // Consultar recursos por nombre
    @GetMapping("/nombre/{nombre}")
    public List<RecursoAcademico> getRecursosByNombre(@PathVariable String nombre) {
        return recursoAcademicoService.getRecursosByNombre(nombre);
    }

    // Consultar recursos por ubicación
    @GetMapping("/ubicacion/{ubicacion}")
    public List<RecursoAcademico> getRecursosByUbicacion(@PathVariable String ubicacion) {
        return recursoAcademicoService.getRecursosByUbicacion(ubicacion);
    }

    // Consultar recursos por ID del responsable
    @GetMapping("/responsable/{personaId}")
    public List<RecursoAcademico> getRecursosByResponsableId(@PathVariable Long personaId) {
        return recursoAcademicoService.getRecursosByResponsableId(personaId);
    }

    // Consultar disponibilidad
    @GetMapping("/{id}/disponibilidad")
    public boolean consultarDisponibilidad(@PathVariable Long id) {
        return recursoAcademicoService.consultarDisponibilidad(id);
    }

    // Cambiar disponibilidad
    @PutMapping("/{id}/disponibilidad")
    public boolean cambiarDisponibilidad(@PathVariable Long id, @RequestParam boolean disponible) {
        return recursoAcademicoService.cambiarDisponibilidad(id, disponible);
    }

    // Verificar rol del responsable
    @GetMapping("/{id}/verificar-rol")
    public boolean verificarRolResponsable(@PathVariable Long id, @RequestParam String rol) {
        return recursoAcademicoService.verificarRolResponsable(id, rol);
    }
}
