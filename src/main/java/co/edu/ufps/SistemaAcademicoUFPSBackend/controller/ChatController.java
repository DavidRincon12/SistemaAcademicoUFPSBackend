package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ChatDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<ChatDTO> getAllChats() {
        return chatService.getAllChats()
                .stream().map(ChatDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDTO> getChatById(@PathVariable Long id) {
        Optional<Chat> chat = chatService.getChatById(id);
        return chat.map(c -> ResponseEntity.ok(new ChatDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChatDTO> createChat(@RequestBody @Valid Chat chat) {
        Chat nuevo = chatService.createChat(chat);
        return ResponseEntity.ok(new ChatDTO(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatDTO> updateChat(@PathVariable Long id, @RequestBody Chat chatDetails) {
        try {
            Chat actualizado = chatService.updateChat(id, chatDetails);
            return ResponseEntity.ok(new ChatDTO(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        try {
            chatService.deleteChat(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/participantes")
    public ResponseEntity<ChatDTO> getChatByParticipantes(@RequestParam Long participante1Id,
                                                          @RequestParam Long participante2Id) {
        Persona p1 = new Persona(); p1.setId(participante1Id);
        Persona p2 = new Persona(); p2.setId(participante2Id);
        Optional<Chat> chat = chatService.findByParticipantes(p1, p2);
        return chat.map(c -> ResponseEntity.ok(new ChatDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/persona/{personaId}")
    public List<ChatDTO> getChatsByPersona(@PathVariable Long personaId) {
        Persona persona = new Persona();
        persona.setId(personaId);
        return chatService.findByPersona(persona)
                .stream().map(ChatDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/fecha")
    public List<ChatDTO> getChatsByFechaCreacionAfter(@RequestParam Date fecha) {
        return chatService.findByFechaCreacionAfter(fecha)
                .stream().map(ChatDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/{chatId}/mensajes")
    public ResponseEntity<Void> agregarMensaje(@PathVariable Long chatId) {
        try {
            chatService.agregarMensaje();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
