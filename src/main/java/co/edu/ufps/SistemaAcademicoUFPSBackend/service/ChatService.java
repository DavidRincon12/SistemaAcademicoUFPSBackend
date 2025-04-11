package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ChatRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private PersonaRepository personaRepository;

    // Obtener todos los chats
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    // Obtener un chat por ID
    public Optional<Chat> getChatById(Long id) {
        return chatRepository.findById(id);
    }

    // Crear un nuevo chat (corrige el error de entidad detached)
    public Chat createChat(Chat chat) {
        Long id1 = chat.getParticipante1().getId();
        Long id2 = chat.getParticipante2().getId();

        Persona participante1 = personaRepository.findById(id1)
                .orElseThrow(() -> new RuntimeException("Participante 1 no encontrado con ID: " + id1));
        Persona participante2 = personaRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("Participante 2 no encontrado con ID: " + id2));

        chat.setParticipante1(participante1);
        chat.setParticipante2(participante2);

        return chatRepository.save(chat);
    }

    // Actualizar un chat
    public Chat updateChat(Long id, Chat chatDetails) {
        return chatRepository.findById(id).map(chat -> {
            chat.setFechaCreacion(chatDetails.getFechaCreacion());

            Long id1 = chatDetails.getParticipante1().getId();
            Long id2 = chatDetails.getParticipante2().getId();

            Persona participante1 = personaRepository.findById(id1)
                    .orElseThrow(() -> new RuntimeException("Participante 1 no encontrado con ID: " + id1));
            Persona participante2 = personaRepository.findById(id2)
                    .orElseThrow(() -> new RuntimeException("Participante 2 no encontrado con ID: " + id2));

            chat.setParticipante1(participante1);
            chat.setParticipante2(participante2);

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

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void agregarMensaje() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
