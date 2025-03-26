package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "Facultad")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class Facultad {

    /**
     * Default constructor
     */
    public Facultad() {
    }


    private String nombre;


    private String correo;


    private Docente decano;

    /**
     * @return
     */
    public Programa crearPrograma() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean eliminarPrograma() {
        // TODO implement here
        return false;
    }

}