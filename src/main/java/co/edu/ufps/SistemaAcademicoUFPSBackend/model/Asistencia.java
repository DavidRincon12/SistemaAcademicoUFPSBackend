package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Asistencia")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class Asistencia {

    /**
     * Default constructor
     */
    public Asistencia() {
    }


    private Estudiante estudiante;
    private Clase clase;
    private Date fecha;
    private String estado;


    public void registrarAsistencia() {
        // TODO implement here
    }


    public void consultarAsistencia() {
        // TODO implement here
    }

}