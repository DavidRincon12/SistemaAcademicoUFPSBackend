package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

public class Asignatura {

    public Asignatura() {
    }

    private String nombre;
    private Docente docente;
    private Materia materia;
    private Estudiante estudiante;
    private Horario horario;
    private Matricula matricula;
    private float primerPrevio;
    private float segundoPrevio;
    private float tercerPrevio;
    private float examenFinal;
    private boolean habilitacion;
    private boolean vacacional;
    private float definitiva;

    /**
     * @return
     */
    public boolean aprovado() {
        // TODO implement here
        return false;
    }

}