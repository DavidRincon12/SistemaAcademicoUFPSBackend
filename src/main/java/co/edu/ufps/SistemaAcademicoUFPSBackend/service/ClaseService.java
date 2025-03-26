package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ClaseService {

    Clase save(Clase clase);

    Clase update(Clase clase);

    void delete(Long id);

    Optional<Clase> findById(Long id);

    List<Clase> findAll();

    List<Clase> findByAsignatura(Asignatura asignatura);

    List<Clase> findByDocente(Docente docente);

    List<Clase> findBySemestre(Semestre semestre);

    List<Clase> findByFecha(Date fecha);

    Optional<Clase> findByNombre(String nombre);

    int contarAsistenciasPorClase(Long claseId);

    List<Clase> findClasesConCupoDisponible();
}
