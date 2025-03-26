package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "HistorialAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

/**
 * 
 */
public class HistorialAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    private Estudiante estudiante;

    // Materias aprobadas
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "historial_materias_aprobadas",
            joinColumns = @JoinColumn(name = "historial_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id"))
    private List<Asignatura> materiasAprobadas = new ArrayList<>();

    // Materias en proceso
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "historial_materias_proceso",
            joinColumns = @JoinColumn(name = "historial_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id"))
    private List<Asignatura> materiasProceso = new ArrayList<>();

    private float promedioPonderado;

    public void calcularPonderado() {
    }


    public void Operation1() {
    }

}