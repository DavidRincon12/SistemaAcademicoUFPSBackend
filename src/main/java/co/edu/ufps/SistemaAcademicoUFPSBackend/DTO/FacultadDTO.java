package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import lombok.Data;

@Data
public class FacultadDTO {
    private Long id;
    private String nombre;
    private String nombreDecano;

    public FacultadDTO(Facultad facultad) {
        this.id = facultad.getId();
        this.nombre = facultad.getNombre();
        this.nombreDecano = facultad.getDecano() != null && facultad.getDecano().getPersona() != null
                ? facultad.getDecano().getPersona().getNombre()
                : null;
    }
}
