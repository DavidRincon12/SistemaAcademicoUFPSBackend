package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@Table(name = "CalendarioAcademico")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class CalendarioAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePeriodo;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    
    public void agregarEvento() {
    }


    public void listarFechasImportantes() {
    }

}