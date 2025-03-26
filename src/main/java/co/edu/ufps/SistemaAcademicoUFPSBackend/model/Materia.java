package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Materia")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private String estado;

    // Pertenece a un semestre
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semestre_id", referencedColumnName = "id")
    private Semestre semestre;

    // Pertenece a un programa
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    private Programa programa;
    private boolean electiva;

    // Materia prerrequisito (relación recursiva)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prerrequisito_id", referencedColumnName = "id")
    private Materia prerrequisito;

    private String contenido;
    private String objetivos;
    private String competencias;
    private short cupoMaximo;
    private short creditos;


    public void añadirAsignatura() {
    }


    public void eliminarAsignatura() {
    }

}