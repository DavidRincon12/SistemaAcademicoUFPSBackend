package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public Asistencia update(Asistencia asistencia) {
        // Aquí puedes agregar lógica adicional (validaciones, etc.) antes de actualizar
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public void delete(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    public Optional<Asistencia> findById(Long id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    public List<Asistencia> findByEstudianteId(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId);
    }

    @Override
    public List<Asistencia> findByClaseId(Long claseId) {
        return asistenciaRepository.findByClaseId(claseId);
    }

    @Override
    public List<Asistencia> findByFecha(Date fecha) {
        return asistenciaRepository.findByFecha(fecha);
    }

    @Override
    public List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId) {
        return asistenciaRepository.findByEstudianteIdAndClaseId(estudianteId, claseId);
    }

    @Override
    public List<Asistencia> findByEstudianteIdAndFechaBetween(Long estudianteId, Date fechaInicio, Date fechaFin) {
        return asistenciaRepository.findByEstudianteIdAndFechaBetween(estudianteId, fechaInicio, fechaFin);
    }

    @Override
    public Optional<Asistencia> findByEstudianteIdAndClaseIdAndFecha(Long estudianteId, Long claseId, Date fecha) {
        return asistenciaRepository.findByEstudianteIdAndClaseIdAndFecha(estudianteId, claseId, fecha);
    }
}
