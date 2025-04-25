package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO {
    private Long id;
    private String nombre;
    private Long docenteId;
    private Long materiaId;

    public AsignaturaDTO(Asignatura asignatura) {
        this.id = asignatura.getId();
        this.nombre = asignatura.getNombre();
        this.docenteId = asignatura.getDocente() != null ? asignatura.getDocente().getId() : null;
        this.materiaId = asignatura.getMateria() != null ? asignatura.getMateria().getId() : null;

    }
}
