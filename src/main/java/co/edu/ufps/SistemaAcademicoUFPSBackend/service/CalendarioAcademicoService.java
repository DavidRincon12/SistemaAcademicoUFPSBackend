package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.CalendarioAcademico;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CalendarioAcademicoService {

    CalendarioAcademico save(CalendarioAcademico calendarioAcademico);

    CalendarioAcademico update(CalendarioAcademico calendarioAcademico);

    void delete(Long id);

    Optional<CalendarioAcademico> findById(Long id);

    List<CalendarioAcademico> findAll();

    Optional<CalendarioAcademico> findByNombrePeriodo(String nombrePeriodo);

    List<CalendarioAcademico> findByFechaDentroDelPeriodo(Date fecha);

    List<CalendarioAcademico> findByPeriodoEntreFechas(Date fechaInicio, Date fechaFin);

    Optional<CalendarioAcademico> findCalendarioActivo();
}
