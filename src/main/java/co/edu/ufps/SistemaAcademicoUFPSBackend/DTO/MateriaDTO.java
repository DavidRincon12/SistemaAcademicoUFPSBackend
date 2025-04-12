package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import lombok.Data;

@Data
public class MateriaDTO {

    private Long id;
    private String nombre;
    private String estado;
    private String nombreSemestre;
    private String nombrePrograma;
    private boolean electiva;
    private String prerrequisito;
    private String contenido;
    private String objetivos;
    private String competencias;
    private short cupoMaximo;
    private short creditos;

    public MateriaDTO(Materia materia) {
        this.id = materia.getId();
        this.nombre = materia.getNombre();
        this.estado = materia.getEstado();
        this.nombreSemestre = materia.getSemestre() != null ? materia.getSemestre().getNombre() : null;
        this.nombrePrograma = materia.getPrograma() != null ? materia.getPrograma().getNombre() : null;
        this.electiva = materia.isElectiva();
        this.prerrequisito = materia.getPrerrequisito() != null ? materia.getPrerrequisito().getNombre() : null;
        this.contenido = materia.getContenido();
        this.objetivos = materia.getObjetivos();
        this.competencias = materia.getCompetencias();
        this.cupoMaximo = materia.getCupoMaximo();
        this.creditos = materia.getCreditos();
    }
}
