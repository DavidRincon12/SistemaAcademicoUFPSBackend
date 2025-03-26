package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Semestre {

    /**
     * Default constructor
     */
    public Semestre() {
    }


    public Date efchaInicio;


    private String nombre;


    private Date fechaFin;


    private CalendarioAcademico calendarioAcademico;

    /**
     * @return
     */
    public boolean validarPeriodoActual() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public CalendarioAcademico asignarCalendario() {
        // TODO implement here
        return null;
    }

}