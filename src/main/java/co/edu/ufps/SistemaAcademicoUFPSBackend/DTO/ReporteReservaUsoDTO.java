package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteReservaUsoDTO {
    private String nombreRecurso;
    private String tipoRecurso;
    private int totalReservas;
    private int totalPrestamos;
}