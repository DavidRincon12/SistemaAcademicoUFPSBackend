package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

    // Buscar clases por asignatura
    List<Clase> findByAsignatura(Asignatura asignatura);

    // Buscar clases por docente
    List<Clase> findByDocente(Docente docente);

    // Buscar clases por semestre
    List<Clase> findBySemestre(Semestre semestre);

    // Buscar clases por fecha
    List<Clase> findByFecha(Date fecha);

    // Buscar clases por nombre
    Optional<Clase> findByNombre(String nombre);

    // Consultar si una clase tiene cupo disponible
    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.clase.id = ?1")
    int contarAsistenciasPorClase(Long claseId);

    @Query("""
        SELECT c FROM Clase c 
        LEFT JOIN Asistencia a ON a.clase = c 
        GROUP BY c 
        HAVING c.cupoMaximo > COUNT(a)
    """)
    List<Clase> findClasesConCupoDisponible();
    
}
