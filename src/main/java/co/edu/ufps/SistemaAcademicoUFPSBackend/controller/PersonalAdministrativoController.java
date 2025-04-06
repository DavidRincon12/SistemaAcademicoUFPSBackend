package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PersonalAdministrativo;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.PersonalAdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("personal-administrativo")
public class PersonalAdministrativoController {

    @Autowired
    private PersonalAdministrativoService personalAdministrativoService;

    /**
     * Obtener todos los registros de personal administrativo.
     * @return Lista de todos los objetos de tipo PersonalAdministrativo.
     */
    @GetMapping
    public List<PersonalAdministrativo> obtenerTodos() {
        return personalAdministrativoService.obtenerTodos();
    }

    /**
     * Obtener un registro de personal administrativo por su ID.
     * @param id ID del personal administrativo.
     * @return El objeto PersonalAdministrativo si se encuentra, o un 404 si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> obtenerPorId(@PathVariable Long id) {
        return personalAdministrativoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtener registros de personal administrativo por su cargo.
     * @param cargo Cargo del personal administrativo.
     * @return Lista de objetos PersonalAdministrativo que coinciden con el cargo.
     */
    @GetMapping("/cargo/{cargo}")
    public List<PersonalAdministrativo> obtenerPorCargo(@PathVariable String cargo) {
        return personalAdministrativoService.obtenerPorCargo(cargo);
    }

    /**
     * Obtener registros de personal administrativo por su departamento.
     * @param departamento Departamento del personal administrativo.
     * @return Lista de objetos PersonalAdministrativo que coinciden con el departamento.
     */
    @GetMapping("/departamento/{departamento}")
    public List<PersonalAdministrativo> obtenerPorDepartamento(@PathVariable String departamento) {
        return personalAdministrativoService.obtenerPorDepartamento(departamento);
    }

    /**
     * Obtener registros de personal administrativo por su área de trabajo.
     * @param area Área de trabajo del personal administrativo.
     * @return Lista de objetos PersonalAdministrativo que coinciden con el área de trabajo.
     */
    @GetMapping("/area-trabajo/{area}")
    public List<PersonalAdministrativo> obtenerPorAreaTrabajo(@PathVariable String area) {
        return personalAdministrativoService.obtenerPorAreaTrabajo(area);
    }

    /**
     * Obtener un registro de personal administrativo por el ID de la persona asociada.
     * @param personaId ID de la persona asociada.
     * @return El objeto PersonalAdministrativo si se encuentra, o un 404 si no existe.
     */
    @GetMapping("/persona/{personaId}")
    public ResponseEntity<PersonalAdministrativo> obtenerPorPersonaId(@PathVariable Long personaId) {
        return personalAdministrativoService.obtenerPorPersonaId(personaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo registro de personal administrativo.
     * @param personalAdministrativo Objeto con los datos del personal administrativo a crear.
     * @return El objeto creado si la operación es exitosa, o un 400 si hay un error.
     */
    @PostMapping
    public ResponseEntity<PersonalAdministrativo> crear(@RequestBody @Valid PersonalAdministrativo personalAdministrativo) {
       return ResponseEntity.ok(personalAdministrativoService.createPersonalAdministrativo(personalAdministrativo));
    }

    /**
     * Actualizar un registro de personal administrativo existente.
     * @param id ID del personal administrativo a actualizar.
     * @param detalles Objeto con los nuevos datos del personal administrativo.
     * @return El objeto actualizado si la operación es exitosa, o un 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> actualizar(@PathVariable Long id, @RequestBody PersonalAdministrativo detalles) {
        try {
            PersonalAdministrativo actualizado = personalAdministrativoService.actualizar(id, detalles);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar un registro de personal administrativo por su ID.
     * @param id ID del personal administrativo a eliminar.
     * @return Un 204 si la operación es exitosa, o un 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            personalAdministrativoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}