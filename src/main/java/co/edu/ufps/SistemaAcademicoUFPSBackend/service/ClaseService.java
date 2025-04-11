package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }

    public Optional<Clase> getClaseById(Long id) {
        return claseRepository.findById(id);
    }

    public Clase createClase(Clase clase) {
        // Asociar entidades existentes desde la base de datos
        Long asignaturaId = clase.getAsignatura().getId();
        Long docenteId = clase.getDocente().getId();
        Long semestreId = clase.getSemestre().getId();

        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con ID: " + asignaturaId));
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + docenteId));
        Semestre semestre = semestreRepository.findById(semestreId)
                .orElseThrow(() -> new RuntimeException("Semestre no encontrado con ID: " + semestreId));

        clase.setAsignatura(asignatura);
        clase.setDocente(docente);
        clase.setSemestre(semestre);

        return claseRepository.save(clase);
    }

    public Clase updateClase(Long id, Clase claseDetails) {
        return claseRepository.findById(id).map(clase -> {
            clase.setNombre(claseDetails.getNombre());
            clase.setAsignatura(claseDetails.getAsignatura());
            clase.setDocente(claseDetails.getDocente());
            clase.setSemestre(claseDetails.getSemestre());
            clase.setFecha(claseDetails.getFecha());
            clase.setCupoMaximo(claseDetails.getCupoMaximo());
            return claseRepository.save(clase);
        }).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    }

    public void deleteClase(Long id) {
        if (!claseRepository.existsById(id)) {
            throw new RuntimeException("Clase no encontrada");
        }
        claseRepository.deleteById(id);
    }

    public List<Clase> findByAsignatura(Asignatura asignatura) {
        return claseRepository.findByAsignatura(asignatura);
    }

    public List<Clase> findByDocente(Docente docente) {
        return claseRepository.findByDocente(docente);
    }

    public List<Clase> findBySemestre(Semestre semestre) {
        return claseRepository.findBySemestre(semestre);
    }

    public List<Clase> findByFecha(Date fecha) {
        return claseRepository.findByFecha(fecha);
    }

    public Optional<Clase> findByNombre(String nombre) {
        return claseRepository.findByNombre(nombre);
    }

    public int contarAsistenciasPorClase(Long claseId) {
        return claseRepository.contarAsistenciasPorClase(claseId);
    }

    public List<Clase> findClasesConCupoDisponible() {
        return claseRepository.findClasesConCupoDisponible();
    }

    @Transactional
    public void iniciarClase() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void finalizarClase() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public void obtenerReporte() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void registrarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public void validarCupo() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
