package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.CalendarioAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarioAcademicoRepository extends JpaRepository<CalendarioAcademico, Long> {

    // Buscar un calendario por su nombre de periodo
    @Query("SELECT c FROM CalendarioAcademico c WHERE c.nombrePeriodo = ?1")
    Optional<CalendarioAcademico> findByNombrePeriodo(String nombrePeriodo);

    // Buscar calendarios que contengan una fecha específica dentro de su periodo
    @Query("SELECT c FROM CalendarioAcademico c WHERE ?1 BETWEEN c.fechaInicio AND c.fechaFin")
    List<CalendarioAcademico> findByFechaDentroDelPeriodo(Date fecha);

    // Buscar calendarios activos en un rango de fechas
    @Query("SELECT c FROM CalendarioAcademico c WHERE c.fechaInicio >= ?1 AND c.fechaFin <= ?2")
    List<CalendarioAcademico> findByPeriodoEntreFechas(Date fechaInicio, Date fechaFin);

    // Buscar el calendario activo en la fecha actual
    @Query("SELECT c FROM CalendarioAcademico c WHERE CURRENT_DATE BETWEEN c.fechaInicio AND c.fechaFin")
    Optional<CalendarioAcademico> findCalendarioActivo();
}
