package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "ReservaRecurso")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class ReservaRecurso {

    /**
     * Default constructor
     */
    public ReservaRecurso() {
    }


    private Date fechaInicio;


    private Date fechaFin;


    private String estado;

    /**
     * @return
     */
    public boolean aprovarReserva() {
        // TODO implement here
        return false;
    }

    /**
     * @param d 
     * @return
     */
    public boolean extenderReserva() {
        // TODO implement here
        return false;
    }

}