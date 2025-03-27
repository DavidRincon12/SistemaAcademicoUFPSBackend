package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Persona> getAllPersons() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Persona> getPersonById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Persona createPerson(Persona persona) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Persona updatePerson(Long id, Persona personaDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deletePerson(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<Persona> findByNumeroDocumento(String numeroDocumento) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Persona> findByCorreo(String correo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Persona> findByNombreContainingIgnoreCase(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Persona> findByRolNombre(String nombreRol) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Persona> findByFechaRegistroAfter(Date fecha) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional(readOnly = true)
    public Optional<Persona> autenticar(String correo, String contrasena) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public int calcularEdad(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void solicitarEmpleo(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void iniciarSesion(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}