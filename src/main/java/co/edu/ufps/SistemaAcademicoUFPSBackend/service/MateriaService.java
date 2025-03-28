package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    // Obtener todas las materias

    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    // Obtener una materia por ID
    public Optional<Materia> getMateriaById(Long id) {
        return materiaRepository.findById(id);
    }
    // ------------------------- Métodos de Negocio -------------------------
    @Transactional
    public void añadirAsignatura() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void eliminarAsignatura() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}