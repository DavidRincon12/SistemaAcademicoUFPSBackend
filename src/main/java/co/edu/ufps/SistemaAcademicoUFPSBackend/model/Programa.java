package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Programa")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Programa {


    private String nombre;


    private String codigo;


    private String duracion;


    private Docente director;


    private String registroSnies;


    private Facultad facultad;

    /**
     * @return
     */
    public Materia crearMateria() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean eliminarMateria() {
        // TODO implement here
        return false;
    }

}