package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDTO {
    private Long id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long asignaturaId; // Solo el ID para no exponer toda la entidad Asignatura
}
