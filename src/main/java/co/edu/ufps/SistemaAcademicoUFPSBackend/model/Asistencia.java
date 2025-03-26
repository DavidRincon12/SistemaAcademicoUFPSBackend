package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@Table(name = "Asistencia")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Registro de asistencia de un estudiante en una clase
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    private Estudiante estudiante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clase_id", referencedColumnName = "id")
    private Clase clase;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String estado;


    public void registrarAsistencia() {
    }


    public void consultarAsistencia() {
    }

}