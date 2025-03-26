package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Long> {

    // Buscar foros por tema (sin importar mayúsculas/minúsculas)
    List<Foro> findByTemaIgnoreCase(String tema);

    // Buscar foros creados por un autor específico
    List<Foro> findByAutorId(Long autorId);

    // Obtener foros creados después de una fecha específica
    List<Foro> findByFechaCreacionAfter(Date fecha);

    // Buscar un foro por su descripción parcial (ignorando mayúsculas/minúsculas)
    @Query("SELECT f FROM Foro f WHERE LOWER(f.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))")
    List<Foro> searchByDescripcion(@Param("descripcion") String descripcion);

    // Contar cuántos foros ha creado un autor
    @Query("SELECT COUNT(f) FROM Foro f WHERE f.autor.id = :autorId")
    long countForosByAutor(@Param("autorId") Long autorId);
}
