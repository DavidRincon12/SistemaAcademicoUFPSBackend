package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import lombok.Data;

@Data
public class ExamenDTO {
    private Long id;
    private String tipo;
    private Long claseId;
    private String claseNombre;
}
