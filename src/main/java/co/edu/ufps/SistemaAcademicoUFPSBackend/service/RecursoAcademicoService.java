package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RecursoAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoAcademicoService {
    @Autowired
    private RecursoAcademicoRepository recursoAcademicoRepository;

    // Obtener todos los recursos académicos

    public List<RecursoAcademico> getAllRecursos() {
        return recursoAcademicoRepository.findAll();
    }

    // Obtener un recurso académico por ID
    public Optional<RecursoAcademico> getRecursoById(Long id) {
        return recursoAcademicoRepository.findById(id);
    }

    @Transactional
    public RecursoAcademico createRecurso(RecursoAcademico recurso) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public RecursoAcademico updateRecurso(Long id, RecursoAcademico recursoDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteRecurso(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<RecursoAcademico> getRecursosByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<RecursoAcademico> getRecursosByUbicacion(String ubicacion) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<RecursoAcademico> getRecursosByResponsableId(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional(readOnly = true)
    public boolean consultarDisponibilidad(Long recursoId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public boolean cambiarDisponibilidad(Long recursoId, boolean nuevaDisponibilidad) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public boolean verificarRolResponsable(Long recursoId, String rolRequerido) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}