package co.edu.ufps.SistemaAcademicoUFPSBackend.DTO;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import lombok.Data;

@Data
public class DocenteDTO {
    private Long id;
    private String correoInstitucional;
    private String tipo;
    private Short cargaHoraria;
    private Long personaId;

    public DocenteDTO(Docente docente) {
        this.id = docente.getId();
        this.correoInstitucional = docente.getCorreoInstitucional();
        this.tipo = docente.getTipo();
        this.cargaHoraria = docente.getCargaHoraria();
        this.personaId = docente.getPersona() != null ? docente.getPersona().getId() : null;
    }
}