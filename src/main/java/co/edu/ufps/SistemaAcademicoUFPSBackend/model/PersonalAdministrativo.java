package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;


@Entity
@Table(name = "PersonalAdministrativo")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros
/**
 * 
 */
public class PersonalAdministrativo {

    /**
     * Default constructor
     */
    public PersonalAdministrativo() {
    }


    private String cargo;


    private Persona persona;

    /**
     * @return
     */
    public void realizarCargo() {
        // TODO implement here
 
    }

}