package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistorialAcademicoServiceImpl implements HistorialAcademicoService {

    private final HistorialAcademicoRepository historialAcademicoRepository;

    public HistorialAcademicoServiceImpl(HistorialAcademicoRepository historialAcademicoRepository) {
        this.historialAcademicoRepository = historialAcademicoRepository;
    }

    @Override
    public HistorialAcademico save(HistorialAcademico historialAcademico) {
        return historialAcademicoRepository.save(historialAcademico);
    }

    @Override
    public HistorialAcademico update(HistorialAcademico historialAcademico) {
        // Se pueden agregar validaciones o lógica adicional antes de actualizar
        return historialAcademicoRepository.save(historialAcademico);
    }

    @Override
    public void delete(Long id) {
        historialAcademicoRepository.deleteById(id);
    }

    @Override
    public Optional<HistorialAcademico> findById(Long id) {
        return historialAcademicoRepository.findById(id);
    }

    @Override
    public Optional<HistorialAcademico> findByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.findByEstudianteId(estudianteId);
    }

    @Override
    public Float findPromedioPonderadoByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.findPromedioPonderadoByEstudianteId(estudianteId);
    }

    @Override
    public int countMateriasAprobadasByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.countMateriasAprobadasByEstudianteId(estudianteId);
    }

    @Override
    public int countMateriasProcesoByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.countMateriasProcesoByEstudianteId(estudianteId);
    }
}
