package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Asignatura")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros


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