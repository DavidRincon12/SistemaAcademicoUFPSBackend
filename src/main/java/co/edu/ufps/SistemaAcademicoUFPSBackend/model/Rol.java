package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Rol")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class Rol {

    /**
     * Default constructor
     */
    public Rol() {
    }


    private String nombre;


    private String permisos;


    private Persona tipo;


    public void revocarPermiso() {
        // TODO implement here
    }

}