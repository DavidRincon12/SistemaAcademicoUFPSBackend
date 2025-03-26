package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClaseServiceImpl implements ClaseService {

    private final ClaseRepository claseRepository;

    public ClaseServiceImpl(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    @Override
    public Clase save(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public Clase update(Clase clase) {
        // Aquí se puede agregar lógica adicional antes de actualizar, por ejemplo, validaciones
        return claseRepository.save(clase);
    }

    @Override
    public void delete(Long id) {
        claseRepository.deleteById(id);
    }

    @Override
    public Optional<Clase> findById(Long id) {
        return claseRepository.findById(id);
    }

    @Override
    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    @Override
    public List<Clase> findByAsignatura(Asignatura asignatura) {
        return claseRepository.findByAsignatura(asignatura);
    }

    @Override
    public List<Clase> findByDocente(Docente docente) {
        return claseRepository.findByDocente(docente);
    }

    @Override
    public List<Clase> findBySemestre(Semestre semestre) {
        return claseRepository.findBySemestre(semestre);
    }

    @Override
    public List<Clase> findByFecha(Date fecha) {
        return claseRepository.findByFecha(fecha);
    }

    @Override
    public Optional<Clase> findByNombre(String nombre) {
        return claseRepository.findByNombre(nombre);
    }

    @Override
    public int contarAsistenciasPorClase(Long claseId) {
        return claseRepository.contarAsistenciasPorClase(claseId);
    }

    @Override
    public List<Clase> findClasesConCupoDisponible() {
        return claseRepository.findClasesConCupoDisponible();
    }
}
