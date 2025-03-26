package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Administrador")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Administrador {

    public Administrador() {
    }


    private Persona persona;
    private CalendarioAcademico calendario;

    /**
     * @param p 
     * @return
     */
    public boolean registrarUsuario(Persona p) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean actualizarInformacion() {
        // TODO implement here
        return false;
    }

    /**
     * @param p  
     * @return
     */
    public boolean eliminarUsuario(Persona p ) {
        // TODO implement here
        return false;
    }

    /**
     * @param p 
     * @return
     */
    public boolean asignarPrivilegios(Persona p) {
        // TODO implement here
        return false;
    }

}