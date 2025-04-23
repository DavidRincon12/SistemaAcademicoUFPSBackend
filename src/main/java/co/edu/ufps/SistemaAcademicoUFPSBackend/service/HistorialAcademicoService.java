package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialAcademicoService {

    @Autowired
    private HistorialAcademicoRepository historialAcademicoRepository;

    @Autowired
    private EstudianteService estudianteService;

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
        return historialAcademicoRepository.save(historialAcademico);
    }

    public void deleteById(Long id) {
        historialAcademicoRepository.deleteById(id);
    }

    public HistorialAcademico update(Long id, HistorialAcademico historialAcademico) {
        Optional<HistorialAcademico> optional = historialAcademicoRepository.findById(id);
        if (optional.isPresent()) {
            HistorialAcademico existente = optional.get();
            existente.setEstudiante(historialAcademico.getEstudiante());
            existente.setAsignatura(historialAcademico.getAsignatura());
            existente.setNota(historialAcademico.getNota());
            existente.setEstado(historialAcademico.getEstado());
            existente.setPeriodo(historialAcademico.getPeriodo());
            return historialAcademicoRepository.save(existente);
        }
        return null;
    }
}