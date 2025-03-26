package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "Persona")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor


public class Persona implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    public void calcularEdad() {
    }
    public void solicitarEmpleo() {
    }
    public void iniciarSesion() {
    }

}