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

    // Buscar una persona por correo electrónico
    Optional<Persona> findByCorreo(String correo);

    // Buscar todas las personas con un rol específico
    List<Persona> findByRol(String rol);

    // Buscar personas cuyo nombre contenga una palabra clave (búsqueda parcial)
    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por número de documento (único)
    Optional<Persona> findByNumeroDocumento(String numeroDocumento);

    // Buscar personas registradas después de cierta fecha
    List<Persona> findByFechaRegistroAfter(Date fecha);

    // Buscar personas que sean de un país específico
    List<Persona> findByNacionalidad(String nacionalidad);

    // Consulta personalizada con JPQL para buscar por email y contraseña (Inicio de sesión)
    @Query("SELECT p FROM Persona p WHERE p.correo = ?1 AND p.contrasena = ?2")
    Optional<Persona> autenticar(String correo, String contrasena);

}
