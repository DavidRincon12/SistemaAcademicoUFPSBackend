package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {

    private Long id;
    private String nombre;
    private String estado;

    private Long semestreId;
    private Long programaId;
    private boolean electiva;

    private Long prerrequisitoId;

    private String contenido;
    private String objetivos;
    private String competencias;

    private short cupoMaximo;
    private short creditos;

    public MateriaDTO(Materia materia) {
        this.id = materia.getId();
        this.nombre = materia.getNombre();
        this.estado = materia.getEstado();
        this.semestreId = materia.getSemestre() != null ? materia.getSemestre().getId() : null;
        this.programaId = materia.getPrograma() != null ? materia.getPrograma().getId() : null;
        this.electiva = materia.isElectiva();
        this.prerrequisitoId = materia.getPrerrequisito() != null ? materia.getPrerrequisito().getId() : null;
        this.contenido = materia.getContenido();
        this.objetivos = materia.getObjetivos();
        this.competencias = materia.getCompetencias();
        this.cupoMaximo = materia.getCupoMaximo();
        this.creditos = materia.getCreditos();
    }
}
