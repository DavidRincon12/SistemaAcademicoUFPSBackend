package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Buscar horarios por día específico
    List<Horario> findByDia(String dia);

    // Buscar horarios dentro de un rango de horas
    @Query("SELECT h FROM Horario h WHERE h.horaInicio >= :horaInicio AND h.horaFin <= :horaFin")
    List<Horario> findByHoraInicioAndHoraFin(@Param("horaInicio") String horaInicio, @Param("horaFin") String horaFin);

    // Buscar horarios de un día específico dentro de un rango de horas
    @Query("SELECT h FROM Horario h WHERE h.dia = :dia AND h.horaInicio >= :horaInicio AND h.horaFin <= :horaFin")
    List<Horario> findByDiaAndHoraInicioAndHoraFin(@Param("dia") String dia, @Param("horaInicio") String horaInicio, @Param("horaFin") String horaFin);
}
