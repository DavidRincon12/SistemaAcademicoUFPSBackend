package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialAcademicoService {
    @Autowired
    private HistorialAcademicoRepository historialAcademicoRepository;

    // Obtener todos los historiales académicos

    public List<HistorialAcademico> getAllHistoriales() {
        return historialAcademicoRepository.findAll();
    }

    // Obtener un historial académico por ID
    public Optional<HistorialAcademico> getHistorialById(Long id) {
        return historialAcademicoRepository.findById(id);
    }

    // Obtener historial académico de un estudiante por su ID
    public Optional<HistorialAcademico> getHistorialByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.findByEstudianteId(estudianteId);
    }

    // Crear un nuevo historial académico
    public HistorialAcademico createHistorial(HistorialAcademico historial) {
        return historialAcademicoRepository.save(historial);
    }

    // Actualizar un historial académico
    public HistorialAcademico updateHistorial(Long id, HistorialAcademico historialDetails) {
        return historialAcademicoRepository.findById(id).map(historial -> {
            historial.setEstudiante(historialDetails.getEstudiante());
            historial.setPromedioPonderado(historialDetails.getPromedioPonderado());
            historial.setMateriasAprobadas(historialDetails.getMateriasAprobadas());
            historial.setMateriasProceso(historialDetails.getMateriasProceso());
            return historialAcademicoRepository.save(historial);
        }).orElseThrow(() -> new RuntimeException("Historial académico no encontrado"));
    }

    // Eliminar historial académico
    public void deleteHistorial(Long id) {
        if (!historialAcademicoRepository.existsById(id)) {
            throw new RuntimeException("Historial académico no encontrado");
        }
        historialAcademicoRepository.deleteById(id);
    }

    // Obtener el promedio ponderado de un estudiante

    public Float getPromedioPonderadoByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.findPromedioPonderadoByEstudianteId(estudianteId);
    }

    // Contar cuántas materias ha aprobado un estudiante
    public int countMateriasAprobadasByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.countMateriasAprobadasByEstudianteId(estudianteId);
    }

    // Contar cuántas materias está cursando actualmente un estudiante
    public int countMateriasProcesoByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.countMateriasProcesoByEstudianteId(estudianteId);
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void calcularPonderado(Long historialId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}