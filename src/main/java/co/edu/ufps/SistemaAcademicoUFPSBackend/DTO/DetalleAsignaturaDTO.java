package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoCurso;
import lombok.Data;

@Data
public class DetalleAsignaturaDTO {
    private String asignaturaNombre;
    private Float nota;
    private EstadoCurso estado;
    private String periodo;
}
