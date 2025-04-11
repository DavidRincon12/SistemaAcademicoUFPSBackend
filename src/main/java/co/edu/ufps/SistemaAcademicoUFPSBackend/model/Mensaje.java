package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Mensaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "emisor_id", referencedColumnName = "id")
    private Persona emisor;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id")
    private Persona destinatario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    @JsonBackReference // evita recursión infinita
    private Chat chat;

    private boolean editado;
    private String estado;
}
