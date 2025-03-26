package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Comentario")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emisor_id", referencedColumnName = "id")
    private Persona emisor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foro_id", referencedColumnName = "id")
    private Foro foro;


    public void editarComentario(String nuevoContenido) {
        this.contenido = nuevoContenido;
        this.fechaCreacion = new Date(); // Actualizar fecha de modificación
    }
    
    public void eliminarComentario() {
        this.contenido = "[Comentario eliminado]"; // Marcar como eliminado en lugar de borrarlo de la BD
    }
    

}