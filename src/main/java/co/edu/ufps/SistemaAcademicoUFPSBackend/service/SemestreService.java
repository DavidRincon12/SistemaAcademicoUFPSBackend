package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SemestreService {

    Semestre save(Semestre semestre);

    Semestre update(Semestre semestre);

    void delete(Long id);

    Optional<Semestre> findById(Long id);

    List<Semestre> findAll();

    Optional<Semestre> findByNombre(String nombre);

    Optional<Semestre> findSemestreActual(Date fechaInicio, Date fechaFin);
}
