package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonaService {

    Persona save(Persona persona);

    Persona update(Persona persona);

    void delete(Long id);

    Optional<Persona> findById(Long id);

    List<Persona> findAll();

    Optional<Persona> findByNumeroDocumento(String numeroDocumento);

    Optional<Persona> findByCorreo(String correo);

    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    List<Persona> findByRolNombre(String nombreRol);

    List<Persona> findByFechaRegistroAfter(Date fecha);

    Optional<Persona> autenticar(String correo, String contrasena);
}
