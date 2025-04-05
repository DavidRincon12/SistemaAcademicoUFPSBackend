package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Persona")
@Data
@NoArgsConstructor
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

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String estadoCivil;
    private String nacionalidad;
    private String datosProfesionales;
    private String genero;
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
