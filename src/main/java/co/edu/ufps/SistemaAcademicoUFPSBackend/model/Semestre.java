package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Semestre")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Semestre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    public Date fechaInicio;


    private String nombre;
    private Date fechaFin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendario_academico_id", referencedColumnName = "id")
    private CalendarioAcademico calendarioAcademico;

    public boolean validarPeriodoActual() {
        return false;
    }

    public CalendarioAcademico asignarCalendario() {
        return null;
    }

}