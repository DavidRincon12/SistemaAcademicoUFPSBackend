package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Comentario;
import lombok.Data;

import java.util.Date;

@Data
public class ComentarioDTO {

    private Long id;
    private String contenido;
    private Date fechaCreacion;

    private Long emisorId;
    private String nombreEmisor;

    private Long foroId;
    private String tituloForo;

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.contenido = comentario.getContenido();
        this.fechaCreacion = comentario.getFechaCreacion();

        this.emisorId = comentario.getEmisor().getId();
        this.nombreEmisor = comentario.getEmisor().getNombre();

        this.foroId = comentario.getForo().getId();
        this.tituloForo = comentario.getForo().getTema();
    }
}
