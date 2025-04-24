package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de inscripción no puede ser nula")
    private LocalDate fechaInscripcion;

    @NotNull(message = "El programa no puede ser nulo")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    private Programa programa;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "El campo becas no puede estar vacío")
    private String becas;

    @NotBlank(message = "El correo estudiantil no puede estar vacío")
    private String correoEstudiantil;

    @NotNull(message = "Los créditos aprobados no pueden ser nulos")
    private Short creditosAprobados;

    @NotNull(message = "La persona no puede ser nula")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<HistorialAcademico> historial = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "estudiante_materia",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> materias = new ArrayList<>();

}
