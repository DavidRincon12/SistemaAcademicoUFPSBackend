package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PersonalAdministrativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalAdministrativo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El cargo no puede estar vacío")
    private String cargo;

    @NotBlank(message = "El departamento no puede estar vacío")
    private String departamento;

    @NotBlank(message = "El área de trabajo no puede estar vacía")
    private String areaTrabajo;

    @NotNull(message = "La fecha de contratación no puede ser nula")
    private LocalDate fechaContratacion;

    @NotNull(message = "La persona no puede ser nula")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;
}
