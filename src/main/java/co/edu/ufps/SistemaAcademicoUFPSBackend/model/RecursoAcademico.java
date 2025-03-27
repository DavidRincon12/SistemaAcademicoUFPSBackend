package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "RecursoAcademico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoAcademico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacionRecurso;
    private String descripcion;
    private boolean disponible;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    private ReservaRecurso reserva;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona responsable;
}