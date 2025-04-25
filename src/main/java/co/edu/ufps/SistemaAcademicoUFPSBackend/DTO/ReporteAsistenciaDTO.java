package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoAsistencia;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReporteAsistenciaDTO {
    private String nombreEstudiante;
    private String correoEstudiantil;
    private String nombreClase;
    private Date fechaClase;
    private String temaVisto;
    private String nombreDocente;
    private EstadoAsistencia estadoAsistencia;
}
