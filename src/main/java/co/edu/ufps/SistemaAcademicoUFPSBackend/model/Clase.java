package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "Clase")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
/**
 * 
 */
public class Clase {

    /**
     * Default constructor
     */
    public Clase() {
    }


    private Asignatura asignatura;
    private Date fecha;
    private String lugar;
    private Semestre semestre;
    private String nombre;
    private short cupoMaximo;
    private short creditos;
    private Docente docente;
    private Asistencia asistencia;
    private String temaVisto;
    
    public void IniciarClase() {
        // TODO implement here
    }


    public void finalizarClase() {
        // TODO implement here
    }


    public void obtenerReporte() {
        // TODO implement here
    }


    public void registrarAsistencia() {
        // TODO implement here
    }


    public void validarCupo() {
        // TODO implement here
    }

}