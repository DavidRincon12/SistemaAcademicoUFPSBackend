package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    // Obtener todos los semestres
    public List<Semestre> getAllSemestres() {
        return semestreRepository.findAll();
    }

    // Obtener un semestre por ID
    public Optional<Semestre> getSemestreById(Long id) {
        return semestreRepository.findById(id);
    }

    // Obtener un semestre por nombre
    public Optional<Semestre> getSemestreByNombre(String nombre) {
        return semestreRepository.findByNombre(nombre);
    }

    // Obtener el semestre actual basado en la fecha actual
    public Optional<Semestre> getSemestreActual() {
        Date fechaActual = new Date();
        return semestreRepository.findByFechaInicioBeforeAndFechaFinAfter(fechaActual, fechaActual);
    }

    // Crear un nuevo semestre
    public Semestre createSemestre(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    // Actualizar un semestre
    public Semestre updateSemestre(Long id, Semestre semestreDetails) {
        return semestreRepository.findById(id).map(semestre -> {
            semestre.setNombre(semestreDetails.getNombre());
            semestre.setFechaInicio(semestreDetails.getFechaInicio());
            semestre.setFechaFin(semestreDetails.getFechaFin());
            return semestreRepository.save(semestre);
        }).orElseThrow(() -> new RuntimeException("Semestre no encontrado"));
    }

    // Eliminar un semestre
    public void deleteSemestre(Long id) {
        if (!semestreRepository.existsById(id)) {
            throw new RuntimeException("Semestre no encontrado");
        }
        semestreRepository.deleteById(id);
    }
}
