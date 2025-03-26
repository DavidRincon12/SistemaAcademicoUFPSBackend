package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "Persona")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class Persona extends Estudiante {

    /**
     * Default constructor
     */
    public Persona() {
    }


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