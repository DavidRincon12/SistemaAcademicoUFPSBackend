package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Foro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Foro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tema;
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Persona autor;

    @OneToMany(mappedBy = "foro", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("foro-comentarios")
    private List<Comentario> comentarios = new ArrayList<>();
}