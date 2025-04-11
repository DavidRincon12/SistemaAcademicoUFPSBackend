package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        // Asegura que tanto docente como materia sean entidades manejadas (attached)
        Docente docente = docenteRepository.findById(asignatura.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        Materia materia = materiaRepository.findById(asignatura.getMateria().getId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        asignatura.setDocente(docente);
        asignatura.setMateria(materia);

        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Long id, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(id).map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setDocente(asignaturaDetails.getDocente());
            asignatura.setMateria(asignaturaDetails.getMateria());
            asignatura.setEstudiantes(asignaturaDetails.getEstudiantes());
            asignatura.setHabilitacion(asignaturaDetails.isHabilitacion());
            asignatura.setVacacional(asignaturaDetails.isVacacional());
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public void deleteAsignatura(Long id) {
        if (!asignaturaRepository.existsById(id)) {
            throw new RuntimeException("Asignatura no encontrada");
        }
        asignaturaRepository.deleteById(id);
    }

    public Optional<Asignatura> findByNombre(String nombre) {
        return asignaturaRepository.findByNombre(nombre);
    }

    public List<Asignatura> findByDocenteId(Long docenteId) {
        return asignaturaRepository.findByDocenteId(docenteId);
    }

    public List<Asignatura> findByMateriaId(Long materiaId) {
        return asignaturaRepository.findByMateriaId(materiaId);
    }

    public List<Asignatura> findByEstudianteId(Long estudianteId) {
        return asignaturaRepository.findByEstudianteId(estudianteId);
    }

    public List<Asignatura> findByHabilitacion() {
        return asignaturaRepository.findByHabilitacionTrue();
    }

    public List<Asignatura> findByVacacional() {
        return asignaturaRepository.findByVacacionalTrue();
    }

    public boolean calcularDefinitiva(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    public boolean verificarAprobacion(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
