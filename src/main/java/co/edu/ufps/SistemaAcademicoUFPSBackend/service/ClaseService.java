package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
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

    // Obtener todas las clases

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }

    // Obtener una clase por ID
    public Optional<Clase> getClaseById(Long id) {
        return claseRepository.findById(id);
    }

    // Crear una nueva clase
    public Clase createClase(Clase clase) {
        return claseRepository.save(clase);
    }

    // Actualizar una clase
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

    // Eliminar una clase
    public void deleteClase(Long id) {
        if (!claseRepository.existsById(id)) {
            throw new RuntimeException("Clase no encontrada");
        }
        claseRepository.deleteById(id);
    }

    // Métodos adicionales requeridos






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
    // ------------------------- Métodos de Negocio -------------------------
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