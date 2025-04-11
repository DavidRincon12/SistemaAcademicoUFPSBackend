package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Clase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "asignatura_id", referencedColumnName = "id")
    private Asignatura asignatura;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private String lugar;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "semestre_id", referencedColumnName = "id")
    private Semestre semestre;

    private String nombre;
    private short cupoMaximo;
    private short creditos;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    private Docente docente;

    @OneToMany(mappedBy = "clase", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Asistencia> asistencias = new ArrayList<>();

    private String temaVisto;
}