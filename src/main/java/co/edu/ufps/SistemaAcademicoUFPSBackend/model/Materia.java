package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Materia")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Materia {




    private String nombre;
    private String estado;
    private Semestre semestre;
    private Programa programa;
    private boolean electiva;
    private Materia prerrequisito;
    private String contenido;
    private String objetivos;
    private String competencias;
    private short cupoMaximo;
    private short creditos;


    public void añadirAsignatura() {
        // TODO implement here
    }


    public void eliminarAsignatura() {
        // TODO implement here
    }

}