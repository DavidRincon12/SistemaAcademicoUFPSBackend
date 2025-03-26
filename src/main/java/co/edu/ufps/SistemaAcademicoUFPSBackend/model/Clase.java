package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Clase")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // La clase se imparte como parte de una asignatura
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asignatura_id", referencedColumnName = "id")
    private Asignatura asignatura;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private String lugar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semestre_id", referencedColumnName = "id")
    private Semestre semestre;

    private String nombre;
    private short cupoMaximo;
    private short creditos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    private Docente docente;

    // Una clase tiene múltiples registros de asistencia
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias = new ArrayList<>();    private String temaVisto;

    public void iniciarClase() {
    }


    public void finalizarClase() {
    }


    public void obtenerReporte() {
    }


    public void registrarAsistencia() {
    }


    public void validarCupo() {
    }

}