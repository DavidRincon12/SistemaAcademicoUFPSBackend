package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "docente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El correo institucional es obligatorio")
    private String correoInstitucional;

    @NotBlank(message = "El tipo de docente es obligatorio")
    private String tipo;

    private short cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @NotNull(message = "Debe asociarse una persona al docente")
    private Persona persona;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HorarioAsesoria> horariosAsesoria;

    private final short cargaHorariaMaxima = 40; // Carga horaria máxima permitida para un docente 
}