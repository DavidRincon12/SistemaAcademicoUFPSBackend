package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Examen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Parcial, Final, Recuperación, etc.

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;
}
