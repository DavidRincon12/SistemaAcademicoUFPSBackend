package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PrestamoMaterial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoMaterial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucionEstimada;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucionReal;

    private String estado; // PRESTADO, DEVUELTO, EN_MANTENIMIENTO

    @ManyToOne
    @JoinColumn(name = "recurso_academico_id")
    private RecursoAcademico recurso;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona solicitante;
}