package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Historial_Academico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialAcademico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    private float nota;

    @Enumerated(EnumType.STRING)
    private EstadoCurso estado; // APROBADO, EN_CURSO, REPROBADO, RETIRADO, etc.

    private String periodo; // Ej: "2024-2", "2025-1"

    @Column(name = "promedio_ponderado")
    private Float promedioPonderado;
}
