package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ForoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ForoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/foros")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @GetMapping
    public List<ForoDTO> getAllForos() {
        return foroService.getAllForos()
                .stream()
                .map(ForoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForoDTO> getForoById(@PathVariable Long id) {
        Optional<Foro> foro = foroService.getForoById(id);
        return foro.map(f -> ResponseEntity.ok(new ForoDTO(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ForoDTO> createForo(@RequestBody @Valid Foro foro) {
        Foro nuevoForo = foroService.createForo(foro);
        return ResponseEntity.ok(new ForoDTO(nuevoForo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForoDTO> updateForo(@PathVariable Long id, @RequestBody Foro foroDetails) {
        try {
            Foro actualizado = foroService.updateForo(id, foroDetails);
            return ResponseEntity.ok(new ForoDTO(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForo(@PathVariable Long id) {
        try {
            foroService.deleteForo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tema")
    public List<ForoDTO> getForosByTema(@RequestParam String tema) {
        return foroService.getForosByTema(tema)
                .stream().map(ForoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/autor/{autorId}")
    public List<ForoDTO> getForosByAutor(@PathVariable Long autorId) {
        return foroService.getForosByAutor(autorId)
                .stream().map(ForoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/fecha")
    public List<ForoDTO> getForosByFechaCreacionAfter(@RequestParam Date fecha) {
        return foroService.getForosByFechaCreacionAfter(fecha)
                .stream().map(ForoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/buscar")
    public List<ForoDTO> searchForosByDescripcion(@RequestParam String descripcion) {
        return foroService.searchForosByDescripcion(descripcion)
                .stream().map(ForoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/autor/{autorId}/conteo")
    public ResponseEntity<Long> countForosByAutor(@PathVariable Long autorId) {
        long cantidad = foroService.countForosByAutor(autorId);
        return ResponseEntity.ok(cantidad);
    }

    @PostMapping("/{foroId}/comentarios")
    public ResponseEntity<Void> agregarComentario(@PathVariable Long foroId,
                                                  @RequestBody @Valid Comentario comentario) {
        try {
            foroService.agregarComentario(foroId, comentario);
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }

    @DeleteMapping("/{foroId}/comentarios/{comentarioId}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long foroId, @PathVariable Long comentarioId) {
        try {
            foroService.eliminarComentario(foroId, comentarioId);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
