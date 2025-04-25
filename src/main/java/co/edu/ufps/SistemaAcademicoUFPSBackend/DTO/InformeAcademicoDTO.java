package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;
import lombok.Data;

import java.util.List;

@Data
public class InformeAcademicoDTO {
    private Long estudianteId;
    private String nombreEstudiante;
    private Float promedioPonderado;
    private int totalAprobadas;
    private int totalReprobadas;
    private int totalEnCurso;
    private List<DetalleAsignaturaDTO> detalle;
}
