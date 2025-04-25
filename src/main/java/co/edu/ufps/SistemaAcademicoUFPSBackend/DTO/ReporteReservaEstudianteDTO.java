package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteReservaEstudianteDTO {
    private String nombreEstudiante;
    private Long idReserva;
    private String recurso;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
}