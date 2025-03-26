package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Administrador")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Administrador {

    private Persona persona;
    private CalendarioAcademico calendario;

    public boolean registrarUsuario(Persona p) {
        // TODO implement here
        return false;
    }

    public boolean actualizarInformacion() {
        // TODO implement here
        return false;
    }


    public boolean eliminarUsuario(Persona p ) {
        // TODO implement here
        return false;
    }


    public boolean asignarPrivilegios(Persona p) {
        // TODO implement here
        return false;
    }

}