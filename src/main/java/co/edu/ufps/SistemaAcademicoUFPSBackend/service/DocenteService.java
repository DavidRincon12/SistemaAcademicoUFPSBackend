package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Docente> getAllDocentes() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Docente> getDocenteById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Docente createDocente(Docente docente) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Docente updateDocente(Long id, Docente docenteDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteDocente(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<Docente> getDocenteByCorreo(String correo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Docente> getDocentesByTipo(String tipo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Docente> getDocentesConAsesoria() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Docente> searchDocentesByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void asignarTrabajo() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void calificarPrevio() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void crearExamen() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}