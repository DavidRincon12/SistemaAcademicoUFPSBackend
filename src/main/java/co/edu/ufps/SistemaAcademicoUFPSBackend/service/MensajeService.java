package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    // Obtener todos los mensajes
    public List<Mensaje> getAllMensajes() {
        return mensajeRepository.findAll();
    }

    // Obtener un mensaje por ID
    public Optional<Mensaje> getMensajeById(Long id) {
        return mensajeRepository.findById(id);
    }

    // Obtener todos los mensajes de un chat ordenados por fecha de envío
    public List<Mensaje> getMensajesByChatId(Long chatId) {
        return mensajeRepository.findByChatIdOrderByFechaEnvioAsc(chatId);
    }

    // Obtener todos los mensajes enviados por un usuario
    public List<Mensaje> getMensajesByEmisorId(Long emisorId) {
        return mensajeRepository.findByEmisorId(emisorId);
    }

    // Obtener todos los mensajes recibidos por un usuario
    public List<Mensaje> getMensajesByDestinatarioId(Long destinatarioId) {
        return mensajeRepository.findByDestinatarioId(destinatarioId);
    }

    // Obtener mensajes entre dos usuarios en un chat específico
    public List<Mensaje> getMensajesEntreUsuariosEnChat(Long chatId, Long usuario1, Long usuario2) {
        return mensajeRepository.findMensajesEntreUsuariosEnChat(chatId, usuario1, usuario2);
    }

    // Obtener los mensajes no leídos de un usuario en un chat
    public List<Mensaje> getMensajesNoLeidos(Long chatId, Long destinatarioId) {
        return mensajeRepository.findMensajesNoLeidos(chatId, destinatarioId);
    }

    // Contar cuántos mensajes no leídos tiene un usuario en un chat
    public long countMensajesNoLeidos(Long chatId, Long destinatarioId) {
        return mensajeRepository.countMensajesNoLeidos(chatId, destinatarioId);
    }

    // Crear un nuevo mensaje
    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    // Actualizar un mensaje
    public Mensaje updateMensaje(Long id, Mensaje mensajeDetails) {
        return mensajeRepository.findById(id).map(mensaje -> {
            mensaje.setContenido(mensajeDetails.getContenido());
            mensaje.setFechaEnvio(mensajeDetails.getFechaEnvio());
            mensaje.setEstado(mensajeDetails.getEstado());
            return mensajeRepository.save(mensaje);
        }).orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
    }

    // Eliminar un mensaje
    public void deleteMensaje(Long id) {
        if (!mensajeRepository.existsById(id)) {
            throw new RuntimeException("Mensaje no encontrado");
        }
        mensajeRepository.deleteById(id);
    }
}
