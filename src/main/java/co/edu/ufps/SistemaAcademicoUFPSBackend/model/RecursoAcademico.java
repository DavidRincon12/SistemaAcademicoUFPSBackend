package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "RecursoAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class RecursoAcademico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String ubicacionRecurso;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    private ReservaRecurso reserva;


    private String descripcion;
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    public boolean consultarDisponibilidad() {
        return false;
    }


    public boolean cambiarDisponibilidad() {
        return false;
    }


    public boolean verificarRol() {
        return false;
    }

}