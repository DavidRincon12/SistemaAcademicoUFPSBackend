package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Semestre> getAllSemestres() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Semestre> getSemestreById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Semestre createSemestre(Semestre semestre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Semestre updateSemestre(Long id, Semestre semestreDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteSemestre(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public Optional<Semestre> getSemestreByNombre(String nombre) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Semestre> getSemestreActual() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Métodos de Negocio -------------------------
    @Transactional(readOnly = true)
    public boolean validarPeriodoActual(Long semestreId) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void asignarCalendario(Long semestreId, Long calendarioId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}