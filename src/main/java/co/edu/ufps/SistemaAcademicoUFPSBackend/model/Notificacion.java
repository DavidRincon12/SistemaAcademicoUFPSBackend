package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@Table(name = "Notificacion")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor


public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    private String contenido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id")
    private Persona destinatario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remitente_id", referencedColumnName = "id")
    private Persona remitente;

    public void marcarComoLeido() {
    }


    public void eliminarNotificacion() {
    }

}