package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    // Buscar materia por nombre
    Optional<Materia> findByNombre(String nombre);

    // Obtener todas las materias de un programa
    List<Materia> findByProgramaId(Long programaId);

    // Obtener todas las materias de un semestre específico
    List<Materia> findBySemestreId(Long semestreId);

    // Obtener todas las materias electivas
    List<Materia> findByElectivaTrue();

    // Obtener todas las materias obligatorias
    List<Materia> findByElectivaFalse();

    // Obtener todas las materias que tienen un prerrequisito
    @Query("SELECT m FROM Materia m WHERE m.prerrequisito IS NOT NULL")
    List<Materia> findMateriasConPrerrequisito();

    // Obtener todas las materias que no tienen prerrequisito
    @Query("SELECT m FROM Materia m WHERE m.prerrequisito IS NULL")
    List<Materia> findMateriasSinPrerrequisito();

    // Obtener materias con cupo disponible
    @Query("SELECT m FROM Materia m WHERE m.cupoMaximo > 0")
    List<Materia> findMateriasConCupoDisponible();

    // Contar cuántas materias tiene un programa
    @Query("SELECT COUNT(m) FROM Materia m WHERE m.programa.id = :programaId")
    long countMateriasByProgramaId(@Param("programaId") Long programaId);
}
