package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Notificacion")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class Notificacion {

    /**
     * Default constructor
     */
    public Notificacion() {
    }


    private Date fechaEnvio;


    private String contenido;


    private Persona destinatario;


    private Persona remitente;


    public void marcarComoLeido() {
        // TODO implement here
    }


    public void eliminarNotificacion() {
        // TODO implement here
    }

}