package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Estudiante")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;

    // Un estudiante pertenece a un programa
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    private Programa programa;

    private String estado;
    private String becas;
    private String correoEstudiantil;
    private Short creditosAprobados;

    // Datos personales
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    public boolean matricular(Asignatura a) {
        return false;
    }


    public short calcularCreditos() {
        return 0;
    }


    public short calcularSemestre() {
        return 0;
    }


    public void solicitarBeca() {
    }


    public boolean validarCupo() {
        return false;
    }

    public boolean validarHorario() {
        return false;
    }


    public boolean validarCreditos() {
        return false;
    }


}