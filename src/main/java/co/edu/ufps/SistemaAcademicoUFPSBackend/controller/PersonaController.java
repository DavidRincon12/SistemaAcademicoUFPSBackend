package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Obtener todas las personas
    @GetMapping
    public List<Persona> getAllPersons() {
        return personaService.getAllPersons();
    }

    // Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersonById(id);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva persona
    @PostMapping
    public Persona createPerson(@RequestBody Persona persona) {
        return personaService.createPerson(persona);
    }

    // Actualizar una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePerson(@PathVariable Long id, @RequestBody Persona personaDetails) {
        try {
            Persona updatedPersona = personaService.updatePerson(id, personaDetails);
            return ResponseEntity.ok(updatedPersona);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        try {
            personaService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar persona por número de documento
    @GetMapping("/documento")
    public ResponseEntity<Persona> findByNumeroDocumento(@RequestParam String numeroDocumento) {
        Optional<Persona> persona = personaService.findByNumeroDocumento(numeroDocumento);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar persona por correo
    @GetMapping("/correo")
    public ResponseEntity<Persona> findByCorreo(@RequestParam String correo) {
        Optional<Persona> persona = personaService.findByCorreo(correo);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar personas por nombre (contiene, ignorando mayúsculas/minúsculas)
    @GetMapping("/buscar")
    public List<Persona> findByNombreContainingIgnoreCase(@RequestParam String nombre) {
        return personaService.findByNombreContainingIgnoreCase(nombre);
    }
    // Buscar personas por rol
    @GetMapping("/roles/{rol}")
    public List<Persona> findByRolId(@PathVariable long id) {
        return personaService.findByRolId(id);
    }
    

    // Buscar personas registradas después de una fecha
    @GetMapping("/fechaRegistro")
    public List<Persona> findByFechaRegistroAfter(@RequestParam Date fecha) {
        return personaService.findByFechaRegistroAfter(fecha);
    }

    // Autenticar persona
    @PostMapping("/autenticar")
    public ResponseEntity<Persona> autenticar(@RequestParam String correo, @RequestParam String contrasena) {
        Optional<Persona> persona = personaService.autenticar(correo, contrasena);
        return persona.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}