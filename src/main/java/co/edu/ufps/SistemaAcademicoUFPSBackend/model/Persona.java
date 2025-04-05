package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.io.Serializable;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String contrasena;

    @NotNull
    private LocalDate fechaRegistro;

    @NotBlank
    private String tipoDocumento;

    @NotBlank
    private String numeroDocumento;

    @NotBlank
    private String direccion;

    @NotBlank
    private String telefono;

    @NotBlank
    @Email
    private String correo;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotBlank
    private String estadoCivil;

    @NotBlank
    private String nacionalidad;

    @NotBlank
    private String datosProfesionales;

    @NotBlank
    private String genero;

    @NotBlank
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @NotNull(message = "Debe especificarse un rol")
    private Rol rol;
}
