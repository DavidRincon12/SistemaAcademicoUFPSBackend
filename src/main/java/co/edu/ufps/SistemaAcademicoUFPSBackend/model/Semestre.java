package co.edu.ufps.SistemaAcademicoUFPSBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Semestre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semestre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToMany(mappedBy = "semestre", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("materia-semestre")
    private List<Materia> materias;

}