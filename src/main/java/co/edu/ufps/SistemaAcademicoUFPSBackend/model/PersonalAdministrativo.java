package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "PersonalAdministrativo")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor

public class PersonalAdministrativo {


    private String cargo;


    private Persona persona;

    /**
     * @return
     */
    public void realizarCargo() {
        // TODO implement here
 
    }

}