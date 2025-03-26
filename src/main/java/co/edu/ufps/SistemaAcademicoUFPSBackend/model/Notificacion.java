package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Notificacion")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor


public class Notificacion {

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