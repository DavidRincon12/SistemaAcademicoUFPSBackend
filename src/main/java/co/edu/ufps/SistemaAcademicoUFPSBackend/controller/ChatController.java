package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // Obtener todos los chats
    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    // Obtener un chat por ID
    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long id) {
        Optional<Chat> chat = chatService.getChatById(id);
        return chat.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo chat
    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody @Valid Chat chat) {
        Chat nuevoChat = chatService.createChat(chat);
        return ResponseEntity.ok(nuevoChat);
    }

    // Actualizar un chat
    @PutMapping("/{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable Long id, @RequestBody Chat chatDetails) {
        try {
            Chat actualizado = chatService.updateChat(id, chatDetails);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un chat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        try {
            chatService.deleteChat(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar un chat entre dos participantes
    // Se espera recibir en el body o parámetro los datos mínimos de las personas
    @GetMapping("/participantes")
    public ResponseEntity<Chat> getChatByParticipantes(@RequestParam Long participante1Id,
                                                       @RequestParam Long participante2Id) {
        // Se construyen objetos Persona mínimos para la búsqueda
        Persona participante1 = new Persona();
        participante1.setId(participante1Id);
        Persona participante2 = new Persona();
        participante2.setId(participante2Id);
        Optional<Chat> chat = chatService.findByParticipantes(participante1, participante2);
        return chat.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar chats en los que una persona está involucrada
    @GetMapping("/persona/{personaId}")
    public List<Chat> getChatsByPersona(@PathVariable Long personaId) {
        Persona persona = new Persona();
        persona.setId(personaId);
        return chatService.findByPersona(persona);
    }

    // Buscar chats creados después de una fecha específica
    @GetMapping("/fecha")
    public List<Chat> getChatsByFechaCreacionAfter(@RequestParam Date fecha) {
        return chatService.findByFechaCreacionAfter(fecha);
    }

    // Método de negocio pendiente: Agregar mensaje a un chat
    @PostMapping("/{chatId}/mensajes")
    public ResponseEntity<Void> agregarMensaje(@PathVariable Long chatId) {
        try {
            chatService.agregarMensaje();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build(); // 501 Not Implemented
        }
    }
}
