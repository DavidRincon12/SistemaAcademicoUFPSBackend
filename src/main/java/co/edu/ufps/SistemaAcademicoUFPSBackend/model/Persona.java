package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Persona")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor


public class Persona extends Estudiante {


    private String nombre;


    private String contrasena;


    private Date fecharegistro;


    private String tipoDocumento;


    private String numeroDocumento;


    private String direccion;


    private String telefono;


    private String correo;


    private Date fechaNacimiento;


    private String estadoCivil;


    private String nacionalidad;


    private String datosProfecionales;


    private String genero;


    private String codigo;


    private Rol rol;


    public void CalcularEdad() {
        // TODO implement here
    }


    public void solicitarEmpleo() {
        // TODO implement here
    }


    public void iniciarSesion() {
        // TODO implement here
    }

}