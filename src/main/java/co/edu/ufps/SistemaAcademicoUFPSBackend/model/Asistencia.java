package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    private Estudiante estudiante;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "clase_id", referencedColumnName = "id")
    @JsonBackReference
    private Clase clase;

    @Enumerated(EnumType.STRING)
    private EstadoAsistencia estado;
}
