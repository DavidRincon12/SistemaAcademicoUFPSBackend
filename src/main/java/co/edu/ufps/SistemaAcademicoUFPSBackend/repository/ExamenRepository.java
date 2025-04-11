package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findByFecha(Date fecha);
    List<Examen> findByRecursoId(Long recursoId);
    List<Examen> findByAsignaturaId(Long asignaturaId);

    @Query("SELECT e FROM Examen e WHERE e.recurso.id = :recursoId AND e.fecha = :fecha AND " +
            "((e.horaInicio < :horaFin AND e.horaFin > :horaInicio))")
    List<Examen> findSolapamientosRecurso(@Param("recursoId") Long recursoId,
                                          @Param("fecha") Date fecha,
                                          @Param("horaInicio") Date horaInicio,
                                          @Param("horaFin") Date horaFin);

    @Query("SELECT e FROM Examen e WHERE e.asignatura.id = :asignaturaId AND e.fecha = :fecha AND " +
            "((e.horaInicio < :horaFin AND e.horaFin > :horaInicio))")
    List<Examen> findSolapamientosAsignatura(@Param("asignaturaId") Long asignaturaId,
                                             @Param("fecha") Date fecha,
                                             @Param("horaInicio") Date horaInicio,
                                             @Param("horaFin") Date horaFin);
}