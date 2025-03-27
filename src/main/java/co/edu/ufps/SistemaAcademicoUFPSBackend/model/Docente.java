package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "Docente")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros


public class Docente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correoInstitucional;
    private String tipo;

    // Datos personales del docente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioAsesoria;

    
    public void asignarTrabajo() {
    }


    public void calificarPrevio() {
    }


    public void crearExamen() {
    }



}