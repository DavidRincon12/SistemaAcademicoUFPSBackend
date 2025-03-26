package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChatService {

    Chat save(Chat chat);

    Chat update(Chat chat);

    void delete(Long id);

    Optional<Chat> findById(Long id);

    List<Chat> findAll();

    Optional<Chat> findByParticipantes(Persona participante1, Persona participante2);

    List<Chat> findByPersona(Persona persona);

    List<Chat> findByFechaCreacionAfter(Date fecha);
}
