package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import lombok.Data;

import java.util.Date;

@Data
public class ForoDTO {

    private Long id;
    private String tema;
    private String descripcion;
    private Date fechaCreacion;

    private Long autorId;
    private String nombreAutor;

    public ForoDTO(Foro foro) {
        this.id = foro.getId();
        this.tema = foro.getTema();
        this.descripcion = foro.getDescripcion();
        this.fechaCreacion = foro.getFechaCreacion();

        this.autorId = foro.getAutor().getId();
        this.nombreAutor = foro.getAutor().getNombre();
    }
}
