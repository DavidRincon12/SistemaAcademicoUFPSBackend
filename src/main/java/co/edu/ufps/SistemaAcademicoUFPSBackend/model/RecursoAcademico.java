package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

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

    // Tipo de recurso: AULA, LABORATORIO, VIRTUAL, LIBRO, EQUIPO
    private String tipoRecurso;

    // Sólo válido si es material (LIBRO o EQUIPO)
    private boolean enMantenimiento;

    @OneToMany(mappedBy = "recursoAcademico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaRecurso> reservas;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona responsable;
}