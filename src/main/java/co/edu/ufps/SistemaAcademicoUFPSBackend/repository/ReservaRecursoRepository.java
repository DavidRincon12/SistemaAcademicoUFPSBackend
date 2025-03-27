package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRecursoRepository extends JpaRepository<ReservaRecurso, Long> {

    // Buscar reservas por estado (ejemplo: "Pendiente", "Aprobada")
    List<ReservaRecurso> findByEstado(String estado);

    // Buscar reservas activas dentro de un rango de fechas
    List<ReservaRecurso> findByFechaInicioBeforeAndFechaFinAfter(Date fechaInicio, Date fechaFin);

    // Buscar reservas por nombre del recurso
    List<ReservaRecurso> findByNombre(String nombre);
}
