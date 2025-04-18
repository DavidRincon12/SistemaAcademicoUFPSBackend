package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Examen;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private ClaseRepository claseRepository;

    public List<Examen> getAllExamenes() {
        return examenRepository.findAll();
    }

    public Optional<Examen> getExamenById(Long id) {
        return examenRepository.findById(id);
    }

    @Transactional
    public Examen createExamen(Examen examen) {
        Clase clase = claseRepository.findById(examen.getClase().getId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        examen.setClase(clase);

        return examenRepository.save(examen);
    }

    public Examen updateExamen(Long id, Examen examenDetails) {
        return examenRepository.findById(id).map(examen -> {
            Clase clase = claseRepository.findById(examenDetails.getClase().getId())
                    .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
            examen.setTipo(examenDetails.getTipo());
            examen.setClase(clase);

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
