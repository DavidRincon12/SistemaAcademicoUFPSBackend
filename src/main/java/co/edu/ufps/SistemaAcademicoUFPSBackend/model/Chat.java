package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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