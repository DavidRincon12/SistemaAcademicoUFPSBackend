package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Estudiante")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Estudiante {



    private Date fechaInscripcion;
    private Programa programa;
    private String estado;
    private String becas;
    private String correoEstudiantil;
    private Short creditosAprobados;
    private Persona persona;

    public boolean matricular(Asignatura a) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public short calcularCreditos() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public short calcularSemestre() {
        // TODO implement here
        return 0;
    }


    public void solicitarBeca() {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean validarCupo() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean validarHorario() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean validarCreditos() {
        // TODO implement here
        return false;
    }


    public void Operation1() {
        // TODO implement here
    }


    public void Operation2() {
        // TODO implement here
    }

}