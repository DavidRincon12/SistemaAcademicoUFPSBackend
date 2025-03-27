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

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Clase> getAllClases() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Clase> getClaseById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Clase createClase(Clase clase) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Clase updateClase(Long id, Clase claseDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteClase(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<Clase> findByAsignatura(Asignatura asignatura) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Clase> findByDocente(Docente docente) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Clase> findBySemestre(Semestre semestre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Clase> findByFecha(Date fecha) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Clase> findByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public int contarAsistenciasPorClase(Long claseId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Clase> findClasesConCupoDisponible() {
        throw new UnsupportedOperationException("Método no implementado");
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