package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Chat;
import lombok.Data;

import java.util.Date;

@Data
public class ChatDTO {

    private Long id;
    private Date fechaCreacion;

    private Long participante1Id;
    private String nombreParticipante1;

    private Long participante2Id;
    private String nombreParticipante2;

    public ChatDTO(Chat chat) {
        this.id = chat.getId();
        this.fechaCreacion = chat.getFechaCreacion();

        this.participante1Id = chat.getParticipante1().getId();
        this.nombreParticipante1 = chat.getParticipante1().getNombre();

        this.participante2Id = chat.getParticipante2().getId();
        this.nombreParticipante2 = chat.getParticipante2().getNombre();
    }
}
