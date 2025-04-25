package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRecursoRepository extends JpaRepository<ReservaRecurso, Long> {

    List<ReservaRecurso> findByEstado(String estado);

    List<ReservaRecurso> findByNombre(String nombre);

    List<ReservaRecurso> findByRecursoAcademicoIdAndEstadoIn(Long recursoId, List<String> estados);

    // 🔄 NUEVO método para obtener reservas activas solapadas
    @Query("SELECT r FROM ReservaRecurso r WHERE r.fechaInicio < :fin AND r.fechaFin > :inicio")
    List<ReservaRecurso> findReservasSolapadas(@Param("inicio") Date inicio, @Param("fin") Date fin);
}
