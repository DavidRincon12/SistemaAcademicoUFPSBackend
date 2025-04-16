package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.InscripcionDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.*;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private HistorialAcademicoRepository historialRepository;

    public InscripcionDTO inscribirEstudiante(Long estudianteId, Long claseId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        // Verificar si ya está inscrito
        if (inscripcionRepository.findByEstudianteAndClaseAndCanceladaFalse(estudiante, clase).isPresent()) {
            throw new RuntimeException("Ya estás inscrito en esta clase.");
        }

        // Validar cupo
        int inscritos = inscripcionRepository.findByClaseAndCanceladaFalse(clase).size();
        if (inscritos >= clase.getCupoMaximo()) {
            throw new RuntimeException("No hay cupos disponibles.");
        }

        // Validar prerrequisitos
        Materia prerrequisito = clase.getAsignatura().getMateria().getPrerrequisito();
        if (prerrequisito != null) {
            HistorialAcademico historial = historialRepository.findByEstudianteId(estudianteId)
                    .orElseThrow(() -> new RuntimeException("Historial académico no encontrado"));
            boolean cumple = historial.getMateriasAprobadas().stream()
                    .anyMatch(a -> a.getMateria().getId().equals(prerrequisito.getId()));
            if (!cumple) {
                throw new RuntimeException("No cumple el prerrequisito.");
            }
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setClase(clase);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setCancelada(false);

        Inscripcion guardada = inscripcionRepository.save(inscripcion);
        return mapToDTO(guardada);
    }
//sirve
    public void cancelarInscripcion(Long estudianteId, Long claseId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        Inscripcion inscripcion = inscripcionRepository.findByEstudianteAndClaseAndCanceladaFalse(estudiante, clase)
                .orElseThrow(() -> new RuntimeException("No estás inscrito en esta clase."));

        inscripcion.setCancelada(true);
        inscripcionRepository.save(inscripcion);
    }

    //sirve
    public List<InscripcionDTO> listarInscripcionesDeEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        List<Inscripcion> inscripciones = inscripcionRepository.findByEstudianteAndCanceladaFalse(estudiante);

        return inscripciones.stream().map(this::mapToDTO).toList();
    }
//sirve
    private InscripcionDTO mapToDTO(Inscripcion i) {
        InscripcionDTO dto = new InscripcionDTO();
        dto.setId(i.getId());
        dto.setNombreEstudiante(i.getEstudiante().getPersona().getNombre());
        dto.setCorreoEstudiantil(i.getEstudiante().getCorreoEstudiantil());
        dto.setNombreClase(i.getClase().getNombre());
        dto.setNombreMateria(i.getClase().getAsignatura().getMateria().getNombre());
        dto.setNombreDocente(i.getClase().getDocente().getPersona().getNombre());
        dto.setFechaInscripcion(i.getFechaInscripcion());
        return dto;
    }
}
