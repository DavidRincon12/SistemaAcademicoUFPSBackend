package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PersonalAdministrativo")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
public class PersonalAdministrativo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;
    private String departamento;
    private String areaTrabajo;

    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    // Método que simula la ejecución de tareas administrativas
    public void realizarTarea(String tarea) {
        System.out.println(persona.getNombre() + " está realizando la tarea: " + tarea);
    }

}
