package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ExamenDTO {
    private Long id;
    private String tipo;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private Long asignaturaId;
    private String asignaturaNombre;
    private Long recursoId;
    private String recursoNombre;
}
