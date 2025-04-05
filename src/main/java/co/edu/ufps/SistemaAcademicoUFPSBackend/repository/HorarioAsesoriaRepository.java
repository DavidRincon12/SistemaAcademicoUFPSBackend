package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria; 
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.repository.query.Param;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Repository
public interface HorarioAsesoriaRepository extends JpaRepository<HorarioAsesoria, Long> {

    @Query("SELECT h FROM HorarioAsesoria h WHERE h.diaSemana = :diaSemana AND :hora BETWEEN h.horaInicio AND h.horaFin")
    List<HorarioAsesoria> findByDiaSemanaAndHora(@Param("diaSemana") DayOfWeek diaSemana, @Param("hora") LocalTime hora);
}