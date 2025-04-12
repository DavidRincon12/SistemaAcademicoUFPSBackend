package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class HistorialAcademicoDTO {

    private Long id;
    private Long estudianteId;
    private String nombreEstudiante;

    private List<String> materiasAprobadas;
    private List<String> materiasEnProceso;

    private float promedioPonderado;

    public HistorialAcademicoDTO(HistorialAcademico h) {
        this.id = h.getId();
        this.estudianteId = h.getEstudiante().getId();
        this.nombreEstudiante = h.getEstudiante().getPersona().getNombre();

        this.promedioPonderado = h.getPromedioPonderado();

        this.materiasAprobadas = h.getMateriasAprobadas().stream()
                .map(asignatura -> asignatura.getMateria().getNombre())
                .collect(Collectors.toList());

        this.materiasEnProceso = h.getMateriasProceso().stream()
                .map(asignatura -> asignatura.getMateria().getNombre())
                .collect(Collectors.toList());
    }
}
