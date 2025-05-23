package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Rol;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements UserDetailsService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Persona usuario = personaRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    
        // Validar que el correo y la contraseña no sean nulos
        if (usuario.getCorreo() == null || usuario.getContrasena() == null) {
            throw new IllegalStateException("El usuario tiene datos incompletos (correo o contraseña nulos)");
        }
    
        // Validar que la contraseña esté codificada
        if (!usuario.getContrasena().startsWith("$2a$")) {
            throw new IllegalStateException("La contraseña del usuario no está codificada correctamente");
        }
    
        // Validar que el usuario tenga un rol asignado
        if (usuario.getRol() == null) {
            throw new IllegalStateException("El usuario no tiene un rol asignado");
        }
    
        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()))
        );
    }

    public Persona asignarRol(Long usuarioId, String nombreRol) {
        Persona usuario = personaRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombre(nombreRol.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rol);
        return personaRepository.save(usuario);
    }

    // Obtener todas las personas

    public List<Persona> getAllPersons() {
        return personaRepository.findAll();
    }

    // Obtener una persona por ID
    public Optional<Persona> getPersonById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona createPerson(Persona persona) {
        if (persona.getRol() != null && persona.getRol().getId() != null) {
            Rol rol = rolRepository.findById(persona.getRol().getId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            persona.setRol(rol); // asignas el rol gestionado por Hibernate
        }

        // Codificar la contraseña si no está ya codificada
        if (persona.getContrasena() != null && !persona.getContrasena().startsWith("$2a$")) {
            String encodedPassword = bCryptPasswordEncoder.encode(persona.getContrasena());
            persona.setContrasena(encodedPassword);
        }

        return personaRepository.save(persona);
    }

    // Actualizar persona
    public Persona updatePerson(Long id, Persona personaDetails) {
        return personaRepository.findById(id).map(persona -> {
            persona.setNombre(personaDetails.getNombre());
            persona.setCorreo(personaDetails.getCorreo());
            persona.setNumeroDocumento(personaDetails.getNumeroDocumento());
            persona.setFechaRegistro(personaDetails.getFechaRegistro());
            return personaRepository.save(persona);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    // Eliminar persona
    public void deletePerson(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("Persona no encontrada");
        }
        personaRepository.deleteById(id);
    }

    // Métodos adicionales necesarios

    public Optional<Persona> findByNumeroDocumento(String numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento);
    }

    public Optional<Persona> findByCorreo(String correo) {
        return personaRepository.findByCorreo(correo);
    }

    public List<Persona> findByNombreContainingIgnoreCase(String nombre) {
        return personaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Persona> findByRolId(Long id) {
        return personaRepository.findByRolId(id);
    }

    public List<Persona> findByFechaRegistroAfter(Date fecha) {
        return personaRepository.findByFechaRegistroAfter(fecha);
    }

    public Optional<Persona> autenticar(String correo, String contrasena) {
        return personaRepository.autenticar(correo, contrasena);

    }
    // ------------------------- Métodos de Negocio -------------------------

    public int calcularEdad(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    public void solicitarEmpleo(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    public void iniciarSesion(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}