package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.TipoRequisito;
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

    private Integer creditosRequeridos;        // nuevo campo
    private String tipoRequisito;              // nuevo campo
    private String requisitosDescripcion;      // nuevo campo

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

        this.creditosRequeridos = materia.getCreditosRequeridos();
        this.tipoRequisito = materia.getTipoRequisito() != null ? materia.getTipoRequisito().name() : "NINGUNO";

        // Generar descripción legible de requisitos
        this.requisitosDescripcion = generarDescripcionRequisitos(materia);
    }

    private String generarDescripcionRequisitos(Materia materia) {
        TipoRequisito tipo = materia.getTipoRequisito();
        if (tipo == null) {
            return "Sin requisitos.";
        }

        String nombrePrerrequisito = materia.getPrerrequisito() != null ? materia.getPrerrequisito().getNombre() : "N/A";
        int creditosReq = materia.getCreditosRequeridos() != null ? materia.getCreditosRequeridos() : 0;

        return switch (tipo) {
            case SOLO_CREDITOS -> "Mínimo " + creditosReq + " créditos aprobados.";
            case SOLO_MATERIA -> "Debe haber cursado la materia: " + nombrePrerrequisito + ".";
            case AMBOS -> "Debe haber cursado la materia: " + nombrePrerrequisito + " y tener al menos " + creditosReq + " créditos aprobados.";
            case NINGUNO -> "Sin requisitos.";
        };
    }
}
