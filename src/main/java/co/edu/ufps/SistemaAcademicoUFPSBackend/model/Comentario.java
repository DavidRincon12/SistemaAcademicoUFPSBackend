package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Comentario")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**

/**
 * 
 */
public class Comentario {

    /**
     * Default constructor
     */
    public Comentario() {
    }


    private String contenido;


    private Date fechaCreacion;


    private Persona emisor;


    private Foro foro;

    /**
     * @param c
     */
    public void editarComentario(Comentario c) {
        // TODO implement here
    }

    /**
     * @param c
     */
    public void eliminarComentario(Comentario c) {
        // TODO implement here
    }

}