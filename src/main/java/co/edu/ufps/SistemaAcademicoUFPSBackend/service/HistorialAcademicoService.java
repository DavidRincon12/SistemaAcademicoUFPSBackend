package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import java.util.Optional;

public interface HistorialAcademicoService {

    HistorialAcademico save(HistorialAcademico historialAcademico);

    HistorialAcademico update(HistorialAcademico historialAcademico);

    void delete(Long id);

    Optional<HistorialAcademico> findById(Long id);

    Optional<HistorialAcademico> findByEstudianteId(Long estudianteId);

    Float findPromedioPonderadoByEstudianteId(Long estudianteId);

    int countMateriasAprobadasByEstudianteId(Long estudianteId);

    int countMateriasProcesoByEstudianteId(Long estudianteId);
}
