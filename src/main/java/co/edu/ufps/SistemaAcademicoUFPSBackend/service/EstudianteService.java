package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private ProgramaRepository programaRepository;
    @Autowired
    private PersonaRepository personaRepository;

    // Obtener todos los estudiantes

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    // Crear un nuevo estudiante
    public Estudiante createEstudiante(Estudiante estudiante) {
        // Verificar que la persona asociada exista
        if (estudiante.getPersona() != null && estudiante.getPersona().getId() != null) {
            Persona persona = personaRepository.findById(estudiante.getPersona().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Persona no encontrada con id: " + estudiante.getPersona().getId()));
            estudiante.setPersona(persona);
        } else {
            throw new RuntimeException("La persona asociada al estudiante es obligatoria");
        }

        // Verificar que el programa asociado exista
        if (estudiante.getPrograma() != null && estudiante.getPrograma().getId() != null) {
            Programa programa = programaRepository.findById(estudiante.getPrograma().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Programa no encontrado con id: " + estudiante.getPrograma().getId()));
            estudiante.setPrograma(programa);
        } else {
            throw new RuntimeException("El programa asociado al estudiante es obligatorio");
        }

        // Guardar el estudiante
        return estudianteRepository.save(estudiante);
    }

    // Actualizar estudiante
    public Estudiante updateEstudiante(Long id, LocalDate fechaInscripcion, String estado, String becas,
            String correoEstudiantil, short creditosAprobados) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.setFechaInscripcion(fechaInscripcion);
            estudiante.setEstado(estado);
            estudiante.setBecas(becas);
            estudiante.setCorreoEstudiantil(correoEstudiantil);
            estudiante.setCreditosAprobados(creditosAprobados);
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    // Eliminar estudiante
    public void deleteEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        estudianteRepository.deleteById(id);
    }

    // Buscar estudiante por correo institucional

    public Optional<Estudiante> getEstudianteByCorreo(String correo) {
        return estudianteRepository.findByCorreoEstudiantil(correo);
    }

    // Obtener estudiantes por estado
    public List<Estudiante> getEstudiantesByEstado(String estado) {
        return estudianteRepository.findByEstado(estado);
    }

    // Obtener estudiantes con becas
    public List<Estudiante> getEstudiantesConBecas() {
        return estudianteRepository.findEstudiantesConBecas();
    }

    // Obtener estudiantes por programa académico
    public List<Estudiante> getEstudiantesByPrograma(Long programaId) {
        return estudianteRepository.findByPrograma(programaId);
    }

    // Buscar estudiantes por nombre
    public List<Estudiante> searchEstudiantesByNombre(String nombre) {
        return estudianteRepository.searchByNombre(nombre);
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public boolean matricular() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public short calcularCreditos() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public short calcularSemestre() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void solicitarBeca() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public boolean validarCupo() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public boolean validarHorario() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public boolean validarCreditos() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}