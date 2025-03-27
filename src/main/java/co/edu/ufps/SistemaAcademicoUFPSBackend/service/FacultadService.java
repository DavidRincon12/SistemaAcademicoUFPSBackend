package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Facultad> getAllFacultades() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Facultad> getFacultadById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Facultad createFacultad(Facultad facultad) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Facultad updateFacultad(Long id, Facultad facultadDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteFacultad(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<Facultad> getFacultadByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Facultad> getFacultadesConDecano() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Facultad> getFacultadByDecanoId(Long decanoId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<Facultad> searchFacultadesByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void crearPrograma() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public boolean eliminarPrograma() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}