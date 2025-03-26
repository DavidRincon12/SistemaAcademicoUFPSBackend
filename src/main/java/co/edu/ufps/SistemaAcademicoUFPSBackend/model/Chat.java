package co.edu.ufps.SistemaAcademicoUFPSBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;


@Entity
@Table(name = "Chat")  // Define la tabla en la BD
@Data
@NoArgsConstructor  // Constructor vacío
@AllArgsConstructor // Constructor con parámetros

public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participante1_id", referencedColumnName = "id")
    private Persona participante1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participante2_id", referencedColumnName = "id")
    private Persona participante2;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes = new ArrayList<>();

    public void agregarMensaje(Mensaje m) {
    }

}