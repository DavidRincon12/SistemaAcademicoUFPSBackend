package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "asignatura_estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaEstudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    @NotNull(message = "Debe asociarse una asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    @NotNull(message = "Debe asociarse un estudiante")
    private Estudiante estudiante;

    @Column(nullable = false)
    private float primerPrevio;

    @Column(nullable = false)
    private float segundoPrevio;

    @Column(nullable = false)
    private float tercerPrevio;

    @Column(nullable = false)
    private float examenFinal;

    @Column(nullable = false)
    private boolean habilitacion;

    @Column(nullable = false)
    private boolean vacacional;

    @Column(nullable = false)
    private float definitiva;
}
