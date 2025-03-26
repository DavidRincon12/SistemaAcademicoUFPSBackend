package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Facultad")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Facultad {


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