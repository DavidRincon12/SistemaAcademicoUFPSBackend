package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "Comentario")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Comentario {

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


    public void editarComentario(Comentario c) {
    }

    public void eliminarComentario(Comentario c) {
    }

}