package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservaRecursoService {

    ReservaRecurso save(ReservaRecurso reservaRecurso);

    ReservaRecurso update(ReservaRecurso reservaRecurso);

    void delete(Long id);

    Optional<ReservaRecurso> findById(Long id);

    List<ReservaRecurso> findAll();

    List<ReservaRecurso> findByEstado(String estado);

    List<ReservaRecurso> findByFechaInicioBeforeAndFechaFinAfter(Date fechaInicio, Date fechaFin);
}
