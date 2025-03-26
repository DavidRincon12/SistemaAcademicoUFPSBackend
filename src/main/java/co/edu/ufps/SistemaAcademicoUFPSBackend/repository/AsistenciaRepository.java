package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    // Buscar asistencias por ID de estudiante
    @Query("SELECT a FROM Asistencia a WHERE a.estudiante.id = ?1")
    List<Asistencia> findByEstudianteId(Long estudianteId);

    // Buscar asistencias por ID de clase
    @Query("SELECT a FROM Asistencia a WHERE a.clase.id = ?1")
    List<Asistencia> findByClaseId(Long claseId);

    // Buscar asistencias por fecha específica
    @Query("SELECT a FROM Asistencia a WHERE a.fecha = ?1")
    List<Asistencia> findByFecha(Date fecha);

    // Buscar asistencias de un estudiante en una clase específica
    @Query("SELECT a FROM Asistencia a WHERE a.estudiante.id = ?1 AND a.clase.id = ?2")
    List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId);

    // Buscar asistencias de un estudiante en un rango de fechas
    @Query("SELECT a FROM Asistencia a WHERE a.estudiante.id = ?1 AND a.fecha BETWEEN ?2 AND ?3")
    List<Asistencia> findByEstudianteIdAndFechaBetween(Long estudianteId, Date fechaInicio, Date fechaFin);

    // Buscar si un estudiante asistió a una clase en una fecha específica
    @Query("SELECT a FROM Asistencia a WHERE a.estudiante.id = ?1 AND a.clase.id = ?2 AND a.fecha = ?3")
    Optional<Asistencia> findByEstudianteIdAndClaseIdAndFecha(Long estudianteId, Long claseId, Date fecha);
}
