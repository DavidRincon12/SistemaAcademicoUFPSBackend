package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    // Buscar por número de documento
    Optional<Persona> findByNumeroDocumento(String numeroDocumento);

    // Buscar por correo electrónico (para validaciones o recuperación de cuenta)
    Optional<Persona> findByCorreo(String correo);

    // Buscar por nombre (pueden existir varias personas con el mismo nombre)
    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por rol (asumiendo que hay una relación entre Persona y Rol)
    List<Persona> findByRolId(Long id);

    // Buscar por fecha de registro después de una fecha dada
    List<Persona> findByFechaRegistroAfter(Date fecha);

    // Consulta personalizada con JPQL para buscar por email y contraseña (Inicio de sesión)
    @Query("SELECT p FROM Persona p WHERE p.correo = ?1 AND p.contrasena = ?2")
    Optional<Persona> autenticar(String correo, String contrasena);
}
