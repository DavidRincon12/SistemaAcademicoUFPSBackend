package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona update(Persona persona) {
        // Se pueden agregar validaciones o lógica adicional antes de actualizar
        return personaRepository.save(persona);
    }

    @Override
    public void delete(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findByNumeroDocumento(String numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento);
    }

    @Override
    public Optional<Persona> findByCorreo(String correo) {
        return personaRepository.findByCorreo(correo);
    }

    @Override
    public List<Persona> findByNombreContainingIgnoreCase(String nombre) {
        return personaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Persona> findByRolNombre(String nombreRol) {
        // En el repository se define el método como findByRol_Nombre,
        // en el service lo podemos renombrar para mayor claridad
        return personaRepository.findByRol_Nombre(nombreRol);
    }

    @Override
    public List<Persona> findByFechaRegistroAfter(Date fecha) {
        return personaRepository.findByFechaRegistroAfter(fecha);
    }

    @Override
    public Optional<Persona> autenticar(String correo, String contrasena) {
        return personaRepository.autenticar(correo, contrasena);
    }
}
