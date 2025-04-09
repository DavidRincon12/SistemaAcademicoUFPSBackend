package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ReservaRecurso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRecurso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    private String estado;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "recurso_academico_id")
    private RecursoAcademico recursoAcademico;
}