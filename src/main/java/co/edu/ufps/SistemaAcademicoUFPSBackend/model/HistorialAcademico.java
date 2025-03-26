package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "HistorialAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class HistorialAcademico {

    /**
     * Default constructor
     */
    public HistorialAcademico() {
    }


    private Estudiante estudiante;
    private Asignatura materiaAprobadas;
    private Asignatura materiaProceso;
    private float promedioPonderado;


    public void calcularPonderado() {
        // TODO implement here
    }


    public void Operation1() {
        // TODO implement here
    }

}