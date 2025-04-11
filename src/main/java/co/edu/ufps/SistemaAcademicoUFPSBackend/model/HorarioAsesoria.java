package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "horarioasesoria")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HorarioAsesoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El día de la semana es obligatorio")
    @Enumerated(EnumType.STRING) // Almacena el nombre del enum como texto (e.g., "MONDAY")
    @Column(name = "dia_semana")
    private DayOfWeek diaSemana;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Column(name = "hora_inicio") // Mapea a la columna "hora_inicio" en la base de datos
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @Column(name = "hora_fin") // Mapea a la columna "hora_fin" en la base de datos
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    @JsonBackReference
    private Docente docente;

}