package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "Facultad")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Facultad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Decano de la facultad
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "decano_id", referencedColumnName = "id")
    private Docente decano;


    public Programa crearPrograma() {
        return null;
    }


    public boolean eliminarPrograma() {
        return false;
    }

}