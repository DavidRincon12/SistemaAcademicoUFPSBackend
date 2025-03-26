package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Horario")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dia;

    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    private Date horaFin;


}