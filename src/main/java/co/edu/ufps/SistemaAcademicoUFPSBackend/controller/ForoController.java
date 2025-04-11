package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foros")
public class ForoController {

    @Autowired
    private ForoService foroService;

    // Obtener todos los foros
    @GetMapping
    public List<Foro> getAllForos() {
        return foroService.getAllForos();
    }

    // Obtener un foro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Foro> getForoById(@PathVariable Long id) {
        Optional<Foro> foro = foroService.getForoById(id);
        return foro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo foro
    @PostMapping
    public ResponseEntity<Foro> createForo(@RequestBody @Valid Foro foro) {
        Foro nuevoForo = foroService.createForo(foro);
        return ResponseEntity.ok(nuevoForo);
    }

    // Actualizar un foro
    @PutMapping("/{id}")
    public ResponseEntity<Foro> updateForo(@PathVariable Long id, @RequestBody Foro foroDetails) {
        try {
            Foro actualizado = foroService.updateForo(id, foroDetails);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un foro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForo(@PathVariable Long id) {
        try {
            foroService.deleteForo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar foros por tema (ignorando mayúsculas/minúsculas)
    @GetMapping("/tema")
    public List<Foro> getForosByTema(@RequestParam String tema) {
        return foroService.getForosByTema(tema);
    }

    // Buscar foros creados por un autor específico
    @GetMapping("/autor/{autorId}")
    public List<Foro> getForosByAutor(@PathVariable Long autorId) {
        return foroService.getForosByAutor(autorId);
    }

    // Obtener foros creados después de una fecha específica
    @GetMapping("/fecha")
    public List<Foro> getForosByFechaCreacionAfter(@RequestParam Date fecha) {
        return foroService.getForosByFechaCreacionAfter(fecha);
    }

    // Buscar foros por descripción parcial
    @GetMapping("/buscar")
    public List<Foro> searchForosByDescripcion(@RequestParam String descripcion) {
        return foroService.searchForosByDescripcion(descripcion);
    }

    // Contar cuántos foros ha creado un autor
    @GetMapping("/autor/{autorId}/conteo")
    public ResponseEntity<Long> countForosByAutor(@PathVariable Long autorId) {
        long cantidad = foroService.countForosByAutor(autorId);
        return ResponseEntity.ok(cantidad);
    }

    // Métodos de negocio: Agregar comentario a un foro
    @PostMapping("/{foroId}/comentarios")
    public ResponseEntity<Void> agregarComentario(@PathVariable Long foroId,
                                                  @RequestBody @Valid Comentario comentario) {
        try {
            foroService.agregarComentario(foroId, comentario);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // 501 Not Implemented
        }
    }

    // Métodos de negocio: Eliminar comentario de un foro
    @DeleteMapping("/{foroId}/comentarios/{comentarioId}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long foroId, @PathVariable Long comentarioId) {
        try {
            foroService.eliminarComentario(foroId, comentarioId);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // 501 Not Implemented
        }
    }
}
