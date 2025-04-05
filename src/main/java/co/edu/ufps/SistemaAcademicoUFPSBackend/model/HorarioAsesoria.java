package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HORARIOASESORIA")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HorarioAsesoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diaSemana; // LUNES, VIERNES, etc.
    private String horaInicio; // "08:00"
    private String horaFin;    // "10:00"

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
}