package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Programa")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String codigo;
    private String duracion;

    // Director del programa (un docente)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Docente director;

    private String registroSnies;

    // El programa pertenece a una facultad
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "facultad_id", referencedColumnName = "id")
    private Facultad facultad;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "materia_id")
    private Materia materia;

    public Materia crearMateria() {
        return null;
    }

    public boolean eliminarMateria() {
        return false;
    }

}