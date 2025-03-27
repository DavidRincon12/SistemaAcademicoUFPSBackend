package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Obtener todos los estudiantes
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    // Crear un nuevo estudiante
    public Estudiante createEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Actualizar estudiante
    public Estudiante updateEstudiante(Long id, Estudiante estudianteDetails) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.setCorreoEstudiantil(estudianteDetails.getCorreoEstudiantil());
            estudiante.setEstado(estudianteDetails.getEstado());
            estudiante.setBecas(estudianteDetails.getBecas());
            estudiante.setPrograma(estudianteDetails.getPrograma());
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    // Eliminar estudiante
    public void deleteEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        estudianteRepository.deleteById(id);
    }

    // Buscar estudiante por correo institucional
    public Optional<Estudiante> getEstudianteByCorreo(String correo) {
        return estudianteRepository.findByCorreoEstudiantil(correo);
    }

    // Obtener estudiantes por estado
    public List<Estudiante> getEstudiantesByEstado(String estado) {
        return estudianteRepository.findByEstado(estado);
    }

    // Obtener estudiantes con becas
    public List<Estudiante> getEstudiantesConBecas() {
        return estudianteRepository.findEstudiantesConBecas();
    }

    // Obtener estudiantes por programa académico
    public List<Estudiante> getEstudiantesByPrograma(Long programaId) {
        return estudianteRepository.findByPrograma(programaId);
    }

    // Buscar estudiantes por nombre
    public List<Estudiante> searchEstudiantesByNombre(String nombre) {
        return estudianteRepository.searchByNombre(nombre);
    }
}
