package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EstudianteDTO {
    private Long id;
    private String becas;
    private String correoEstudiantil;
    private Short creditosAprobados;
    private String estado;
    private LocalDate fechaInscripcion;
    private Long personaId;
    private Long programaId;

    public EstudianteDTO(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.becas = estudiante.getBecas();
        this.correoEstudiantil = estudiante.getCorreoEstudiantil();
        this.creditosAprobados = estudiante.getCreditosAprobados();
        this.estado = estudiante.getEstado();
        this.fechaInscripcion = estudiante.getFechaInscripcion();
        this.personaId = estudiante.getPersona() != null ? estudiante.getPersona().getId() : null;
        this.programaId = estudiante.getPrograma() != null ? estudiante.getPrograma().getId() : null;
    }
}
