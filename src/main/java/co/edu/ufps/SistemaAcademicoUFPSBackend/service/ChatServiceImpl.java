package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat update(Chat chat) {
        // Aquí puedes agregar lógica adicional, validaciones o transformaciones
        return chatRepository.save(chat);
    }

    @Override
    public void delete(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public Optional<Chat> findById(Long id) {
        return chatRepository.findById(id);
    }

    @Override
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public Optional<Chat> findByParticipantes(Persona participante1, Persona participante2) {
        return chatRepository.findByParticipantes(participante1, participante2);
    }

    @Override
    public List<Chat> findByPersona(Persona persona) {
        return chatRepository.findByPersona(persona);
    }

    @Override
    public List<Chat> findByFechaCreacionAfter(Date fecha) {
        return chatRepository.findByFechaCreacionAfter(fecha);
    }
}
