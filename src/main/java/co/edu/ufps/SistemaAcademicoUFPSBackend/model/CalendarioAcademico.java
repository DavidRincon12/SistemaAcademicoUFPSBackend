package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "CalendarioAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class CalendarioAcademico {

    /**
     * Default constructor
     */
    public CalendarioAcademico() {
    }


    private String nombrePeriodo;


    private Date fechaInicio;


    private Date fechaFin;


    public void agregarEvento() {
        // TODO implement here
    }


    public void listarFechasImportantes() {
        // TODO implement here
    }

}