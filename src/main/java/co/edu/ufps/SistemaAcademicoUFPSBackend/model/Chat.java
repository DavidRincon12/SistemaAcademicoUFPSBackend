package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "Chat")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
/**
 * 
 */
public class Chat {

    /**
     * Default constructor
     */
    public Chat() {
    }


    private Date fechaCreacion;
    private Persona participante1;
    private Persona participante2;

    /**
     * @param m
     */
    public void agregarMensaje(Mensaje m) {
        // TODO implement here
    }

}