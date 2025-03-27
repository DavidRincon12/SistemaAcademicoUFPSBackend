package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Mensaje> getAllMensajes() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Mensaje> getMensajeById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Mensaje createMensaje(Mensaje mensaje) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Mensaje updateMensaje(Long id, Mensaje mensajeDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteMensaje(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesByChatId(Long chatId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesByEmisorId(Long emisorId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesByDestinatarioId(Long destinatarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesEntreUsuariosEnChat(Long chatId, Long usuario1, Long usuario2) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesNoLeidos(Long chatId, Long destinatarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public long countMensajesNoLeidos(Long chatId, Long destinatarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void editarMensaje(Long mensajeId, String nuevoContenido) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void eliminarMensaje(Long mensajeId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void cambiarEstado(Long mensajeId, String nuevoEstado) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}