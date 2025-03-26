package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Asignatura")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros


public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Asignatura es impartida por un docente
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    private Docente docente;

    // Relación con la materia
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "materia_id", referencedColumnName = "id")
    private Materia materia;

    // Una asignatura puede tener muchos estudiantes inscritos
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "asignatura_estudiante",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private List<Estudiante> estudiantes = new ArrayList<>();

    // Horario en el que se imparte
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horario_id", referencedColumnName = "id")
    private Horario horario;

    private float primerPrevio;
    private float segundoPrevio;
    private float tercerPrevio;
    private float examenFinal;
    private boolean habilitacion;
    private boolean vacacional;
    private float definitiva;


    public boolean aprobado() {
        return false;
    }

}