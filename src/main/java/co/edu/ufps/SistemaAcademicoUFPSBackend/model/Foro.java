package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Foro")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class Foro {

    /**
     * Default constructor
     */
    public Foro() {
    }


    private String tema;
    private String descripcion;
    private Date fechaCreacion;
    private Persona autor;

    /**
     * @param c
     */
    public void agregarComentario(Comentario c) {
        // TODO implement here
    }

    /**
     * @param c
     */
    public void eliminarComentario(Comentario c) {
        // TODO implement here
    }

}