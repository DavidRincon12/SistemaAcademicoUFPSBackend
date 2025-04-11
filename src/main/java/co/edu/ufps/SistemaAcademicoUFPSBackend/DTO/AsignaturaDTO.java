package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO {
    private Long id;
    private String nombre;
    private Long docenteId;
    private Long materiaId;
    private List<Long> estudiantesIds;
    private float primerPrevio;
    private float segundoPrevio;
    private float tercerPrevio;
    private float examenFinal;
    private boolean habilitacion;
    private boolean vacacional;
    private float definitiva;

    public AsignaturaDTO(Asignatura asignatura) {
        this.id = asignatura.getId();
        this.nombre = asignatura.getNombre();
        this.docenteId = asignatura.getDocente() != null ? asignatura.getDocente().getId() : null;
        this.materiaId = asignatura.getMateria() != null ? asignatura.getMateria().getId() : null;
        this.estudiantesIds = asignatura.getEstudiantes() != null
                ? asignatura.getEstudiantes().stream()
                .map(Estudiante::getId)
                .collect(Collectors.toList())
                : null;
        this.primerPrevio = asignatura.getPrimerPrevio();
        this.segundoPrevio = asignatura.getSegundoPrevio();
        this.tercerPrevio = asignatura.getTercerPrevio();
        this.examenFinal = asignatura.getExamenFinal();
        this.habilitacion = asignatura.isHabilitacion();
        this.vacacional = asignatura.isVacacional();
        this.definitiva = asignatura.getDefinitiva();
    }
}
