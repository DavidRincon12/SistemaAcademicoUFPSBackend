package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ComentarioDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<ComentarioDTO> getAllComentarios() {
        return comentarioService.getAllComentarios()
                .stream().map(ComentarioDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        return comentario.map(c -> ResponseEntity.ok(new ComentarioDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> createComentario(@RequestBody @Valid Comentario comentario) {
        Comentario nuevoComentario = comentarioService.createComentario(comentario);
        return ResponseEntity.ok(new ComentarioDTO(nuevoComentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(@PathVariable Long id,
                                                          @RequestBody Comentario comentarioDetails) {
        try {
            Comentario actualizado = comentarioService.updateComentario(id, comentarioDetails);
            return ResponseEntity.ok(new ComentarioDTO(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        try {
            comentarioService.deleteComentario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/foro")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByForo(@RequestParam Long foroId) {
        Foro foro = new Foro();
        foro.setId(foroId);
        List<ComentarioDTO> comentarios = comentarioService.findByForo(foro)
                .stream().map(ComentarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/emisor")
    public ResponseEntity<List<ComentarioDTO>> getComentariosByEmisor(@RequestParam Long emisorId) {
        Persona emisor = new Persona();
        emisor.setId(emisorId);
        List<ComentarioDTO> comentarios = comentarioService.findByEmisor(emisor)
                .stream().map(ComentarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/foro/{foroId}/recientes")
    public ResponseEntity<List<ComentarioDTO>> getRecentCommentsByForo(@PathVariable Long foroId) {
        Foro foro = new Foro();
        foro.setId(foroId);
        List<ComentarioDTO> comentarios = comentarioService.findRecentCommentsByForo(foro)
                .stream().map(ComentarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ComentarioDTO>> searchComentariosByContenido(@RequestParam String contenido) {
        List<ComentarioDTO> comentarios = comentarioService.searchByContenido(contenido)
                .stream().map(ComentarioDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(comentarios);
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<Void> editarComentario(@PathVariable Long id,
                                                 @RequestParam String nuevoContenido) {
        try {
            comentarioService.editarComentario(id, nuevoContenido);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

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
