package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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