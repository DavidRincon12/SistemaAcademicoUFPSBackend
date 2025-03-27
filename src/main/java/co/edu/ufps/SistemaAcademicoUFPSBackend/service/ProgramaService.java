package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Programa> getAllProgramas() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Programa> getProgramaById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Programa createPrograma(Programa programa) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Programa updatePrograma(Long id, Programa programaDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deletePrograma(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<Programa> getProgramaByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Programa> getProgramaByCodigo(String codigo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Programa> getProgramasByFacultadId(Long facultadId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void crearMateria() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void eliminarMateria() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}