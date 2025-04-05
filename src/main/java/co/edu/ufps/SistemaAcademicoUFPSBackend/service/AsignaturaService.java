package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // Obtener todas las asignaturas

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    // Obtener una asignatura por ID
    public Optional<Asignatura> getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id);
    }

    // Crear una nueva asignatura
    public Asignatura createAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    // Actualizar una asignatura
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

    // Eliminar una asignatura
    public void deleteAsignatura(Long id) {
        if (!asignaturaRepository.existsById(id)) {
            throw new RuntimeException("Asignatura no encontrada");
        }
        asignaturaRepository.deleteById(id);
    }

    // Métodos adicionales requeridos

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

        // ------------------------- Métodos de Negocio -------------------------
    public boolean calcularDefinitiva(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    public boolean verificarAprobacion(Long asignaturaId) {
        throw new UnsupportedOperationException("Método no implementado");
        }

    }