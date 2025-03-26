package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Mensaje")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor

public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emisor_id", referencedColumnName = "id")
    private Persona emisor;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id")
    private Persona destinatario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat;

    private boolean editado;
    private String estado;


    public void editarMensaje() {
    }


    public void eliminarMensaje() {
    }


    public void cambiarEstado() {
    }

}