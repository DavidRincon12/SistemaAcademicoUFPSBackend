package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Docente")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
/**
 * 
 */
public class Docente {


    private String correoInstucional;
    private String tipo;
    private Persona persona;
    private Date horarioAsesoria;

    
    public void asignarTrabajo() {
        // TODO implement here
    }


    public void calificarPrevio() {
        // TODO implement here
    }


    public void crearExamen() {
        // TODO implement here
    }


    public void Operation1() {
        // TODO implement here
    }

}