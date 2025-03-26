package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Mensaje")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class Mensaje {

    /**
     * Default constructor
     */
    public Mensaje() {
    }


    private String contenido;
    private Persona emisor;
    private Persona destinatario;
    private Date fechaEnvio;
    private Chat chat;
    private boolean editado;
    private String estado;


    public void editarMensaje() {
        // TODO implement here
    }


    public void eliminarMensaje() {
        // TODO implement here
    }


    public void cambiarEstado() {
        // TODO implement here
    }

}