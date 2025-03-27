package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    // Obtener todos los docentes
    public List<Docente> getAllDocentes() {
        return docenteRepository.findAll();
    }

    // Obtener un docente por ID
    public Optional<Docente> getDocenteById(Long id) {
        return docenteRepository.findById(id);
    }

    // Crear un nuevo docente
    public Docente createDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    // Actualizar un docente
    public Docente updateDocente(Long id, Docente docenteDetails) {
        return docenteRepository.findById(id).map(docente -> {
            docente.setCorreoInstitucional(docenteDetails.getCorreoInstitucional());
            docente.setTipo(docenteDetails.getTipo());
            docente.setHorarioAsesoria(docenteDetails.getHorarioAsesoria());
            return docenteRepository.save(docente);
        }).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    // Eliminar un docente
    public void deleteDocente(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new RuntimeException("Docente no encontrado");
        }
        docenteRepository.deleteById(id);
    }

    // Buscar un docente por su correo institucional
    public Optional<Docente> getDocenteByCorreo(String correo) {
        return docenteRepository.findByCorreoInstitucional(correo);
    }

    // Obtener docentes por tipo
    public List<Docente> getDocentesByTipo(String tipo) {
        return docenteRepository.findByTipo(tipo);
    }

    // Obtener docentes con asesorías disponibles
    public List<Docente> getDocentesConAsesoria() {
        return docenteRepository.findDocentesConAsesoria();
    }

    // Buscar docentes por nombre
    public List<Docente> searchDocentesByNombre(String nombre) {
        return docenteRepository.searchByNombre(nombre);
    }
}
