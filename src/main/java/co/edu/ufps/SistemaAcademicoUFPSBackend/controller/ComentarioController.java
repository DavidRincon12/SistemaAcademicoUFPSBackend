package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // ---------------------- Endpoints CRUD Básicos ----------------------

    // Obtener todos los comentarios
    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioService.getAllComentarios();
    }

    // Obtener un comentario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        return comentario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo comentario
    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody @Valid Comentario comentario) {
        Comentario nuevoComentario = comentarioService.createComentario(comentario);
        return ResponseEntity.ok(nuevoComentario);
    }

    // Actualizar un comentario existente
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id,
                                                       @RequestBody Comentario comentarioDetails) {
        try {
            Comentario actualizado = comentarioService.updateComentario(id, comentarioDetails);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un comentario (eliminación CRUD)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        try {
            comentarioService.deleteComentario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ---------------------- Endpoints para Consultas Adicionales ----------------------

    // Buscar comentarios por foro
    // Se espera recibir el ID del foro vía parámetro
    @GetMapping("/foro")
    public ResponseEntity<List<Comentario>> getComentariosByForo(@RequestParam Long foroId) {
        Foro foro = new Foro();
        foro.setId(foroId);
        List<Comentario> comentarios = comentarioService.findByForo(foro);
        return ResponseEntity.ok(comentarios);
    }

    // Buscar comentarios por emisor
    @GetMapping("/emisor")
    public ResponseEntity<List<Comentario>> getComentariosByEmisor(@RequestParam Long emisorId) {
        Persona emisor = new Persona();
        emisor.setId(emisorId);
        List<Comentario> comentarios = comentarioService.findByEmisor(emisor);
        return ResponseEntity.ok(comentarios);
    }

    // Obtener comentarios recientes de un foro
    @GetMapping("/foro/{foroId}/recientes")
    public ResponseEntity<List<Comentario>> getRecentCommentsByForo(@PathVariable Long foroId) {
        Foro foro = new Foro();
        foro.setId(foroId);
        List<Comentario> comentarios = comentarioService.findRecentCommentsByForo(foro);
        return ResponseEntity.ok(comentarios);
    }

    // Buscar comentarios por contenido (búsqueda parcial ignorando mayúsculas/minúsculas)
    @GetMapping("/buscar")
    public ResponseEntity<List<Comentario>> searchComentariosByContenido(@RequestParam String contenido) {
        List<Comentario> comentarios = comentarioService.searchByContenido(contenido);
        return ResponseEntity.ok(comentarios);
    }

    // ---------------------- Endpoints para Métodos de Negocio (Pendientes) ----------------------

    // Endpoint para editar el contenido de un comentario mediante lógica de negocio
    // Se pasa el nuevo contenido vía parámetro
    @PutMapping("/{id}/editar")
    public ResponseEntity<Void> editarComentario(@PathVariable Long id,
                                                 @RequestParam String nuevoContenido) {
        try {
            comentarioService.editarComentario(id, nuevoContenido);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();  // 501 Not Implemented
        }
    }

    // Endpoint para eliminar un comentario mediante lógica de negocio
    @DeleteMapping("/{id}/eliminar-negocio")
    public ResponseEntity<Void> eliminarComentarioNegocio(@PathVariable Long id) {
        try {
            comentarioService.eliminarComentario(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
