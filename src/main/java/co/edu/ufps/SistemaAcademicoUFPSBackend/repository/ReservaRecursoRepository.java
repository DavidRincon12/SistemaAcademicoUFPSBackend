package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRecursoRepository extends JpaRepository<ReservaRecurso, Long> {
    List<ReservaRecurso> findByEstado(String estado);
    List<ReservaRecurso> findByFechaInicioBeforeAndFechaFinAfter(Date fechaInicio, Date fechaFin);
    List<ReservaRecurso> findByNombre(String nombre);
    // Nuevo: buscar reservas de un recurso por estados determinados
    List<ReservaRecurso> findByRecursoAcademicoIdAndEstadoIn(Long recursoId, List<String> estados);
}
