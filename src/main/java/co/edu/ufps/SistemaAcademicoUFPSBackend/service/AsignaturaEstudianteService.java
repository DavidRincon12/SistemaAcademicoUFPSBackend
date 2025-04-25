package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.*;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaEstudianteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaEstudianteService {

    @Autowired
    private AsignaturaEstudianteRepository asignaturaEstudianteRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private HistorialAcademicoService historialAcademicoService;

    @Autowired
    private HistorialAcademicoRepository historialAcademicoRepository;

    public List<AsignaturaEstudiante> getAllAsignaturasEstudiantes() {
        return asignaturaEstudianteRepository.findAll();
    }

    public Optional<AsignaturaEstudiante> getById(Long id) {
        return asignaturaEstudianteRepository.findById(id);
    }

    public AsignaturaEstudiante create(AsignaturaEstudiante ae) {
        if (ae.getEstudiante() != null && ae.getEstudiante().getId() != null) {
            Estudiante estudiante = estudianteRepository.findById(ae.getEstudiante().getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + ae.getEstudiante().getId()));
            ae.setEstudiante(estudiante);
        }

        if (ae.getAsignatura() != null && ae.getAsignatura().getId() != null) {
            Asignatura asignatura = asignaturaRepository.findById(ae.getAsignatura().getId())
                    .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con ID: " + ae.getAsignatura().getId()));
            ae.setAsignatura(asignatura);
        }

        // Calcular nota definitiva automáticamente
        float notaPrevia = (ae.getPrimerPrevio() + ae.getSegundoPrevio() + ae.getTercerPrevio()) / 3.0f;
        float definitiva = (notaPrevia * 0.7f) + (ae.getExamenFinal() * 0.3f);
        ae.setDefinitiva(definitiva);

        AsignaturaEstudiante saved = asignaturaEstudianteRepository.save(ae);

        // Calcular el estado del curso
        EstadoCurso estado = definitiva >= 3.0f ? EstadoCurso.APROBADO : EstadoCurso.REPROBADO;

        // Crear historial académico
        HistorialAcademico historial = new HistorialAcademico();
        historial.setEstudiante(saved.getEstudiante());
        historial.setAsignatura(saved.getAsignatura());
        historial.setNota(definitiva);
        historial.setEstado(estado);
        historial.setPeriodo("2025-1"); // Puedes automatizarlo si lo deseas

        // Calcular y guardar promedio ponderado
        Float promedio = historialAcademicoRepository.calcularPromedioPonderado(saved.getEstudiante().getId());
        historial.setPromedioPonderado(promedio != null ? promedio : 0f);

        historialAcademicoService.save(historial);

        return saved;
    }

    public AsignaturaEstudiante update(Long id, AsignaturaEstudiante detalles) {
        return asignaturaEstudianteRepository.findById(id).map(ae -> {
            ae.setPrimerPrevio(detalles.getPrimerPrevio());
            ae.setSegundoPrevio(detalles.getSegundoPrevio());
            ae.setTercerPrevio(detalles.getTercerPrevio());
            ae.setExamenFinal(detalles.getExamenFinal());

            // Calcular nota definitiva automáticamente
            float notaPrevia = (detalles.getPrimerPrevio() + detalles.getSegundoPrevio() + detalles.getTercerPrevio()) / 3.0f;
            float definitiva = (notaPrevia * 0.7f) + (detalles.getExamenFinal() * 0.3f);
            ae.setDefinitiva(definitiva);

            ae.setHabilitacion(detalles.isHabilitacion());
            ae.setVacacional(detalles.isVacacional());

            AsignaturaEstudiante actualizado = asignaturaEstudianteRepository.save(ae);

            // Buscar historial existente o crear uno nuevo
            List<HistorialAcademico> existentes = historialAcademicoRepository.findByEstudianteId(ae.getEstudiante().getId());
            HistorialAcademico historial = existentes.stream()
                    .filter(h -> h.getAsignatura().getId().equals(ae.getAsignatura().getId()))
                    .findFirst()
                    .orElse(new HistorialAcademico());

            historial.setEstudiante(ae.getEstudiante());
            historial.setAsignatura(ae.getAsignatura());
            historial.setNota(definitiva);
            historial.setEstado(definitiva >= 3.0f ? EstadoCurso.APROBADO : EstadoCurso.REPROBADO);
            historial.setPeriodo("2025-1");

            Float promedio = historialAcademicoRepository.calcularPromedioPonderado(ae.getEstudiante().getId());
            historial.setPromedioPonderado(promedio != null ? promedio : 0f);

            historialAcademicoService.save(historial);

            return actualizado;
        }).orElseThrow(() -> new RuntimeException("AsignaturaEstudiante no encontrada"));
    }

    public void delete(Long id) {
        if (!asignaturaEstudianteRepository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado");
        }
        asignaturaEstudianteRepository.deleteById(id);
    }

    public List<AsignaturaEstudiante> getByEstudianteId(Long estudianteId) {
        return asignaturaEstudianteRepository.findByEstudianteId(estudianteId);
    }

    public List<AsignaturaEstudiante> getByAsignaturaId(Long asignaturaId) {
        return asignaturaEstudianteRepository.findByAsignaturaId(asignaturaId);
    }

    public String cancelarAsignatura(Long estudianteId, Long asignaturaId) {
        List<AsignaturaEstudiante> registros = asignaturaEstudianteRepository.findByEstudianteId(estudianteId)
                .stream()
                .filter(ae -> ae.getAsignatura().getId().equals(asignaturaId))
                .toList();

        if (registros.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una relación entre el estudiante y la asignatura.");
        }

        // También eliminar historial académico asociado
        List<HistorialAcademico> historiales = historialAcademicoRepository.findByEstudianteId(estudianteId)
                .stream()
                .filter(h -> h.getAsignatura().getId().equals(asignaturaId))
                .toList();

        historialAcademicoRepository.deleteAll(historiales);
        asignaturaEstudianteRepository.deleteAll(registros);

        return "Asignatura y su historial académico cancelados correctamente para el estudiante.";
    }
}
