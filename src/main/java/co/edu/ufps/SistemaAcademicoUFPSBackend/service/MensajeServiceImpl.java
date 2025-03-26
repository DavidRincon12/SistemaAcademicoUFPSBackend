package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MensajeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;

    public MensajeServiceImpl(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    @Override
    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public Mensaje update(Mensaje mensaje) {
        // Puedes agregar lógica adicional o validaciones antes de actualizar
        return mensajeRepository.save(mensaje);
    }

    @Override
    public void delete(Long id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    public Optional<Mensaje> findById(Long id) {
        return mensajeRepository.findById(id);
    }

    @Override
    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    @Override
    public List<Mensaje> findByChatIdOrderByFechaEnvioAsc(Long chatId) {
        return mensajeRepository.findByChatIdOrderByFechaEnvioAsc(chatId);
    }

    @Override
    public List<Mensaje> findByEmisorId(Long emisorId) {
        return mensajeRepository.findByEmisorId(emisorId);
    }

    @Override
    public List<Mensaje> findByDestinatarioId(Long destinatarioId) {
        return mensajeRepository.findByDestinatarioId(destinatarioId);
    }

    @Override
    public List<Mensaje> findMensajesEntreUsuariosEnChat(Long chatId, Long usuario1, Long usuario2) {
        return mensajeRepository.findMensajesEntreUsuariosEnChat(chatId, usuario1, usuario2);
    }

    @Override
    public List<Mensaje> findMensajesNoLeidos(Long chatId, Long destinatarioId) {
        return mensajeRepository.findMensajesNoLeidos(chatId, destinatarioId);
    }

    @Override
    public long countMensajesNoLeidos(Long chatId, Long destinatarioId) {
        return mensajeRepository.countMensajesNoLeidos(chatId, destinatarioId);
    }
}
