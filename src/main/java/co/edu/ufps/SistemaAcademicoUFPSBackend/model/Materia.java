package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String estado;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "semestre_id", referencedColumnName = "id")
    private Semestre semestre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    private Programa programa;

    private boolean electiva;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "prerrequisito_id", referencedColumnName = "id")
    private Materia prerrequisito;

    private String contenido;
    private String objetivos;
    private String competencias;
    private short cupoMaximo;
    private short creditos;
}