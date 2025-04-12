package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDTO {

    private Long id;
    private String nombreEstudiante;
    private String correoEstudiantil;
    private String nombreClase;
    private String nombreMateria;
    private String nombreDocente;
    private LocalDateTime fechaInscripcion;
}
