package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a WHERE a.estudiante.id = ?1")
    List<Asistencia> findByEstudianteId(Long estudianteId);

    @Query("SELECT a FROM Asistencia a WHERE a.clase.id = ?1")
    List<Asistencia> findByClaseId(Long claseId);

    @Query("SELECT a FROM Asistencia a WHERE a.estudiante.id = ?1 AND a.clase.id = ?2")
    List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId);

    List<Asistencia> findByEstado(EstadoAsistencia estado);

    List<Asistencia> findByClaseIdAndEstado(Long claseId, EstadoAsistencia estado);
}
