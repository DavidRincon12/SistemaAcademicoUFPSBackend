package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import java.util.List;
import java.util.Optional;

public interface MensajeService {

    Mensaje save(Mensaje mensaje);

    Mensaje update(Mensaje mensaje);

    void delete(Long id);

    Optional<Mensaje> findById(Long id);

    List<Mensaje> findAll();

    List<Mensaje> findByChatIdOrderByFechaEnvioAsc(Long chatId);

    List<Mensaje> findByEmisorId(Long emisorId);

    List<Mensaje> findByDestinatarioId(Long destinatarioId);

    List<Mensaje> findMensajesEntreUsuariosEnChat(Long chatId, Long usuario1, Long usuario2);

    List<Mensaje> findMensajesNoLeidos(Long chatId, Long destinatarioId);

    long countMensajesNoLeidos(Long chatId, Long destinatarioId);
}
