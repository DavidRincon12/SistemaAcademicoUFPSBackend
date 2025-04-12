package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Mensaje;
import lombok.Data;

import java.util.Date;

@Data
public class MensajeDTO {
    private Long id;
    private String contenido;
    private Date fechaEnvio;
    private boolean editado;
    private String estado;

    private Long chatId;
    private Long emisorId;
    private String nombreEmisor;
    private Long destinatarioId;
    private String nombreDestinatario;

    public MensajeDTO(Mensaje mensaje) {
        this.id = mensaje.getId();
        this.contenido = mensaje.getContenido();
        this.fechaEnvio = mensaje.getFechaEnvio();
        this.editado = mensaje.isEditado();
        this.estado = mensaje.getEstado();
        this.chatId = mensaje.getChat().getId();

        this.emisorId = mensaje.getEmisor().getId();
        this.nombreEmisor = mensaje.getEmisor().getNombre();

        this.destinatarioId = mensaje.getDestinatario().getId();
        this.nombreDestinatario = mensaje.getDestinatario().getNombre();
    }
}
