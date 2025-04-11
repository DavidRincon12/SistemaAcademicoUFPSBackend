package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;


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

    @OneToMany(mappedBy = "recursoAcademico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaRecurso> reservas;
    //Un recurso académico tiene muchas reservas
    //y si se elimina el recurso, también se eliminan sus reservas

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona responsable;
}