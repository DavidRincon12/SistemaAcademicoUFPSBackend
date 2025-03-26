package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Foro")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros


public class Foro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tema;
    private String descripcion;
    private Date fechaCreacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Persona autor;

    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    public void agregarComentario(Comentario c) {
    }

    public void eliminarComentario(Comentario c) {
    }

}