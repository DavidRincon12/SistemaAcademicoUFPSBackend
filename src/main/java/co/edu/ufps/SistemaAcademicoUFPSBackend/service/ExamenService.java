package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Examen;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ExamenRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RecursoAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private RecursoAcademicoRepository recursoAcademicoRepository;

    public List<Examen> getAllExamenes() {
        return examenRepository.findAll();
    }

    public Optional<Examen> getExamenById(Long id) {
        return examenRepository.findById(id);
    }

    @Transactional
    public Examen createExamen(Examen examen) {
        Asignatura asignatura = asignaturaRepository.findById(examen.getAsignatura().getId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        examen.setAsignatura(asignatura);

        if (examen.getRecurso() != null) {
            RecursoAcademico recurso = recursoAcademicoRepository.findById(examen.getRecurso().getId())
                    .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
            examen.setRecurso(recurso);

            // Validar solapamiento con otros exámenes en el mismo recurso
            List<Examen> solapadosRecurso = examenRepository.findSolapamientosRecurso(
                    recurso.getId(), examen.getFecha(), examen.getHoraInicio(), examen.getHoraFin());
            if (!solapadosRecurso.isEmpty()) {
                throw new RuntimeException("El recurso académico ya está reservado en ese horario.");
            }
        }

        // Validar solapamiento con otros exámenes de la misma asignatura
        List<Examen> solapadosAsignatura = examenRepository.findSolapamientosAsignatura(
                asignatura.getId(), examen.getFecha(), examen.getHoraInicio(), examen.getHoraFin());
        if (!solapadosAsignatura.isEmpty()) {
            throw new RuntimeException("Ya hay otro examen programado para esta asignatura en ese horario.");
        }

        return examenRepository.save(examen);
    }

    public Examen updateExamen(Long id, Examen examenDetails) {
        return examenRepository.findById(id).map(examen -> {
            examen.setTipo(examenDetails.getTipo());
            examen.setFecha(examenDetails.getFecha());
            examen.setHoraInicio(examenDetails.getHoraInicio());
            examen.setHoraFin(examenDetails.getHoraFin());

            Asignatura asignatura = asignaturaRepository.findById(examenDetails.getAsignatura().getId())
                    .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
            examen.setAsignatura(asignatura);

            if (examenDetails.getRecurso() != null) {
                RecursoAcademico recurso = recursoAcademicoRepository.findById(examenDetails.getRecurso().getId())
                        .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
                examen.setRecurso(recurso);
            } else {
                examen.setRecurso(null);
            }

            return examenRepository.save(examen);
        }).orElseThrow(() -> new RuntimeException("Examen no encontrado"));
    }

    public void deleteExamen(Long id) {
        if (!examenRepository.existsById(id)) {
            throw new RuntimeException("Examen no encontrado");
        }
        examenRepository.deleteById(id);
    }
}
