package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Semestre")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Semestre {


    public Date fechaInicio;


    private String nombre;


    private Date fechaFin;

    private CalendarioAcademico calendarioAcademico;


    public boolean validarPeriodoActual() {
        // TODO implement here
        return false;
    }


    public CalendarioAcademico asignarCalendario() {
        // TODO implement here
        return null;
    }

}