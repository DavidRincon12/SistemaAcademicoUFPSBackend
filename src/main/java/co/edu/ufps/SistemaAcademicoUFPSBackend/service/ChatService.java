package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    // Obtener todos los chats
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    // Obtener un chat por ID
    public Optional<Chat> getChatById(Long id) {
        return chatRepository.findById(id);
    }

    // Crear un nuevo chat
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    // Actualizar un chat
    public Chat updateChat(Long id, Chat chatDetails) {
        return chatRepository.findById(id).map(chat -> {
            chat.setParticipante1(chatDetails.getParticipante1());
            chat.setParticipante2(chatDetails.getParticipante2());
            chat.setFechaCreacion(chatDetails.getFechaCreacion());
            return chatRepository.save(chat);
        }).orElseThrow(() -> new RuntimeException("Chat no encontrado"));
    }

    // Eliminar un chat
    public void deleteChat(Long id) {
        if (!chatRepository.existsById(id)) {
            throw new RuntimeException("Chat no encontrado");
        }
        chatRepository.deleteById(id);
    }

    // Métodos adicionales requeridos

    public Optional<Chat> findByParticipantes(Persona participante1, Persona participante2) {
        return chatRepository.findByParticipantes(participante1, participante2);
    }

    public List<Chat> findByPersona(Persona persona) {
        return chatRepository.findByPersona(persona);
    }

    public List<Chat> findByFechaCreacionAfter(Date fecha) {
        return chatRepository.findByFechaCreacionAfter(fecha);
    }
}
