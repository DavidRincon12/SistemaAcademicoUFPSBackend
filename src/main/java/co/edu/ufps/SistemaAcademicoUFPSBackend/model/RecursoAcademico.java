package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "RecursoAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class RecursoAcademico {

    /**
     * Default constructor
     */
    public RecursoAcademico() {
    }


    private String ubicacionREcurso;


    private ReservaRecurso reserva;


    private String descripcion;


    private String nombre;


    private Persona persona;

    /**
     * @return
     */
    public boolean consultarDisponibilidad() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean cambiarDisponibilidad() {
        // TODO implement here
        return false;
    }


    public void Operation1() {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean verificarRol() {
        // TODO implement here
        return false;
    }

}