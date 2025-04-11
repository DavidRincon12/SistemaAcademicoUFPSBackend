package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialAcademicoService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private HistorialAcademicoRepository historialAcademicoRepository;

    public List<HistorialAcademico> findAll() {
        return historialAcademicoRepository.findAll();
    }

    public HistorialAcademico findById(Long id) {
        return historialAcademicoRepository.findById(id).orElse(null);
    }

    public HistorialAcademico save(HistorialAcademico historialAcademico) {
        Long estudianteId = historialAcademico.getEstudiante().getId();
        if (estudianteId != null) {
            var estudianteExistente = estudianteService.getEstudianteById(estudianteId)
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + estudianteId));

            historialAcademico.setEstudiante(estudianteExistente);
        }

        if (historialAcademico.getMateriasAprobadas() == null) {
            historialAcademico.setMateriasAprobadas(new ArrayList<>());
        }

        if (historialAcademico.getMateriasProceso() == null) {
            historialAcademico.setMateriasProceso(new ArrayList<>());
        }

        return historialAcademicoRepository.save(historialAcademico);
    }

    public void deleteById(Long id) {
        historialAcademicoRepository.deleteById(id);
    }

    public HistorialAcademico update(Long id, HistorialAcademico historialAcademico) {
        Optional<HistorialAcademico> optional = historialAcademicoRepository.findById(id);
        if (optional.isPresent()) {
            HistorialAcademico existente = optional.get();

            if (historialAcademico.getMateriasAprobadas() == null) {
                historialAcademico.setMateriasAprobadas(new ArrayList<>());
            }

            if (historialAcademico.getMateriasProceso() == null) {
                historialAcademico.setMateriasProceso(new ArrayList<>());
            }

            existente.setEstudiante(historialAcademico.getEstudiante());
            existente.setMateriasAprobadas(historialAcademico.getMateriasAprobadas());
            existente.setMateriasProceso(historialAcademico.getMateriasProceso());

            return historialAcademicoRepository.save(existente);
        }
        return null;
    }

    @Transactional
    public HistorialAcademico agregarAsignaturaAprobada(Long historialId, Long asignaturaId) {
        HistorialAcademico historial = historialAcademicoRepository.findById(historialId)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        if (!historial.getMateriasAprobadas().contains(asignatura)) {
            historial.getMateriasAprobadas().add(asignatura);
        }

        return historialAcademicoRepository.save(historial);
    }

    @Transactional
    public HistorialAcademico agregarAsignaturaEnProceso(Long historialId, Long asignaturaId) {
        HistorialAcademico historial = historialAcademicoRepository.findById(historialId)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        if (!historial.getMateriasProceso().contains(asignatura)) {
            historial.getMateriasProceso().add(asignatura);
        }

        return historialAcademicoRepository.save(historial);
    }

}
