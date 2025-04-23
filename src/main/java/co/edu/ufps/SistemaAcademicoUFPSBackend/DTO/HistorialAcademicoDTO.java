package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoCurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoCurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import lombok.Data;

@Data
public class HistorialAcademicoDTO {

    private Long id;
    private Long estudianteId;
    private String nombreEstudiante;

    private Long asignaturaId;
    private String nombreAsignatura;

    private float nota;
    private EstadoCurso estado;
    private String periodo;

    public HistorialAcademicoDTO(HistorialAcademico h) {
        this.id = h.getId();
        this.estudianteId = h.getEstudiante().getId();
        this.nombreEstudiante = h.getEstudiante().getPersona().getNombre();

        this.asignaturaId = h.getAsignatura().getId();
        this.nombreAsignatura = h.getAsignatura().getMateria().getNombre();

        this.nota = h.getNota();
        this.estado = h.getEstado(); // directamente el enum
        this.periodo = h.getPeriodo();
    }
}
