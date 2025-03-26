package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "Administrador")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Administrador tiene una Persona (datos de usuario)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    // Gestiona el calendario académico
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendario_id", referencedColumnName = "id")
    private CalendarioAcademico calendario;

    public boolean registrarUsuario(Persona p) {
        return false;
    }

    public boolean actualizarInformacion() {
        return false;
    }


    public boolean eliminarUsuario(Persona p ) {
        return false;
    }


    public boolean asignarPrivilegios(Persona p) {
        return false;
    }

}