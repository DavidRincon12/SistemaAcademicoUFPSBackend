package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import lombok.Data;

import java.util.Date;

@Data
public class ClaseDTO {

    private Long id;
    private String nombre;
    private Date fecha;
    private String lugar;
    private String temaVisto;
    private short cupoMaximo;
    private short creditos;

    private String nombreAsignatura;
    private String nombreDocente;
    private String nombreSemestre;

    public ClaseDTO(Clase clase) {
        this.id = clase.getId();
        this.nombre = clase.getNombre();
        this.fecha = clase.getFecha();
        this.lugar = clase.getLugar();
        this.temaVisto = clase.getTemaVisto();
        this.cupoMaximo = clase.getCupoMaximo();
        this.creditos = clase.getCreditos();

        this.nombreAsignatura = clase.getAsignatura() != null ? clase.getAsignatura().getNombre() : null;
        this.nombreDocente = clase.getDocente() != null && clase.getDocente().getPersona() != null
                ? clase.getDocente().getPersona().getNombre() : null;
        this.nombreSemestre = clase.getSemestre() != null ? clase.getSemestre().getNombre() : null;
    }
}
