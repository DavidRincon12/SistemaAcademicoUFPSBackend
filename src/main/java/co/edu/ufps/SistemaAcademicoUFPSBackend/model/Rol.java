package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Rol")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Rol {


    private String nombre;


    private String permisos;


    private Persona tipo;


    public void revocarPermiso() {
        // TODO implement here
    }

}