package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "ReservaRecurso")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class ReservaRecurso {



    private Date fechaInicio;


    private Date fechaFin;


    private String estado;

    /**
     * @return
     */
    public boolean aprovarReserva() {
        // TODO implement here
        return false;
    }

    /**
     * @param d 
     * @return
     */
    public boolean extenderReserva() {
        // TODO implement here
        return false;
    }

}