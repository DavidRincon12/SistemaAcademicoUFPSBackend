package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import lombok.Data;

import java.util.Date;

@Data
public class AsistenciaDTO {

    private Long id;
    private Long estudianteId;
    private String nombreEstudiante;
    private Long claseId;
    private String nombreClase;
    private Date fecha;
    private String estado;

    public AsistenciaDTO(Asistencia asistencia) {
        this.id = asistencia.getId();
        this.estudianteId = asistencia.getEstudiante().getId();
        this.nombreEstudiante = asistencia.getEstudiante().getPersona().getNombre();
        this.claseId = asistencia.getClase().getId();
        this.nombreClase = asistencia.getClase().getNombre();
        this.fecha = asistencia.getFecha();
        this.estado = asistencia.getEstado();
    }
}
