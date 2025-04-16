package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.AsignaturaEstudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaEstudianteDTO {

    private Long id;
    private Long estudianteId;
    private Long asignaturaId;
    private float primerPrevio;
    private float segundoPrevio;
    private float tercerPrevio;
    private float examenFinal;
    private boolean habilitacion;
    private boolean vacacional;
    private float definitiva;

    public AsignaturaEstudianteDTO(AsignaturaEstudiante ae) {
        this.id = ae.getId();
        this.estudianteId = ae.getEstudiante().getId();
        this.asignaturaId = ae.getAsignatura().getId();
        this.primerPrevio = ae.getPrimerPrevio();
        this.segundoPrevio = ae.getSegundoPrevio();
        this.tercerPrevio = ae.getTercerPrevio();
        this.examenFinal = ae.getExamenFinal();
        this.habilitacion = ae.isHabilitacion();
        this.vacacional = ae.isVacacional();
        this.definitiva = ae.getDefinitiva();
    }
}
