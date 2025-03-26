package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "Horario")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class Horario {

    /**
     * Default constructor
     */
    public Horario() {
    }


    public Materia materia;
    private String dia;
    private Date horaInicio;
    private Date horaFin;

}