package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "ReservaRecurso")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class ReservaRecurso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    private String estado;


    public boolean aprobarReserva() {
        return false;
    }

    public boolean extenderReserva() {
        return false;
    }

}