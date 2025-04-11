package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ChatRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MensajeRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ChatRepository chatRepository;

    public List<Mensaje> getAllMensajes() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> getMensajeById(Long id) {
        return mensajeRepository.findById(id);
    }

    @Transactional
    public Mensaje createMensaje(Mensaje mensaje) {
        Persona emisor = personaRepository.findById(mensaje.getEmisor().getId())
                .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));

        Persona destinatario = personaRepository.findById(mensaje.getDestinatario().getId())
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        Chat chat = chatRepository.findById(mensaje.getChat().getId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));

        mensaje.setEmisor(emisor);
        mensaje.setDestinatario(destinatario);
        mensaje.setChat(chat);
        mensaje.setFechaEnvio(new Date());
        mensaje.setEstado("ENVIADO");
        mensaje.setEditado(false);

        return mensajeRepository.save(mensaje);
    }

    @Transactional
    public void editarMensaje(Long mensajeId, String nuevoContenido) {
        Mensaje mensaje = mensajeRepository.findById(mensajeId)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        mensaje.setContenido(nuevoContenido);
        mensaje.setEditado(true);
        mensajeRepository.save(mensaje);
    }

    @Transactional
    public void eliminarMensaje(Long mensajeId) {
        if (!mensajeRepository.existsById(mensajeId)) {
            throw new RuntimeException("Mensaje no encontrado");
        }
        mensajeRepository.deleteById(mensajeId);
    }

    @Transactional
    public void cambiarEstado(Long mensajeId, String nuevoEstado) {
        Mensaje mensaje = mensajeRepository.findById(mensajeId)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        mensaje.setEstado(nuevoEstado);
        mensajeRepository.save(mensaje);
    }
}
