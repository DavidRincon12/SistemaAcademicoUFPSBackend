package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String contrasena;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    
    private String tipoDocumento;
    private String numeroDocumento;
    private String direccion;
    private String telefono;
    private String correo;
    private Date fechaNacimiento;
    private String estadoCivil;
    private String nacionalidad;
    private String datosProfesionales;
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