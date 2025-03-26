package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AsistenciaService {

    Asistencia save(Asistencia asistencia);

    Asistencia update(Asistencia asistencia);

    void delete(Long id);

    Optional<Asistencia> findById(Long id);

    List<Asistencia> findAll();

    List<Asistencia> findByEstudianteId(Long estudianteId);

    List<Asistencia> findByClaseId(Long claseId);

    List<Asistencia> findByFecha(Date fecha);

    List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId);

    List<Asistencia> findByEstudianteIdAndFechaBetween(Long estudianteId, Date fechaInicio, Date fechaFin);

    Optional<Asistencia> findByEstudianteIdAndClaseIdAndFecha(Long estudianteId, Long claseId, Date fecha);
}
