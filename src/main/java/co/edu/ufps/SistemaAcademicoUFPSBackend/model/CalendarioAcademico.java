package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "CalendarioAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class CalendarioAcademico {



    private String nombrePeriodo;
    private Date fechaInicio;
    private Date fechaFin;

    
    public void agregarEvento() {
        // TODO implement here
    }


    public void listarFechasImportantes() {
        // TODO implement here
    }

}