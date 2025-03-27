package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Buscar materia por nombre
    public Optional<Materia> getMateriaByNombre(String nombre) {
        return materiaRepository.findByNombre(nombre);
    }

    // Obtener todas las materias de un programa
    public List<Materia> getMateriasByProgramaId(Long programaId) {
        return materiaRepository.findByProgramaId(programaId);
    }

    // Obtener todas las materias de un semestre específico
    public List<Materia> getMateriasBySemestreId(Long semestreId) {
        return materiaRepository.findBySemestreId(semestreId);
    }

    // Obtener todas las materias electivas
    public List<Materia> getMateriasElectivas() {
        return materiaRepository.findByElectivaTrue();
    }

    // Obtener todas las materias obligatorias
    public List<Materia> getMateriasObligatorias() {
        return materiaRepository.findByElectivaFalse();
    }

    // Obtener todas las materias que tienen un prerrequisito
    public List<Materia> getMateriasConPrerrequisito() {
        return materiaRepository.findMateriasConPrerrequisito();
    }

    // Obtener todas las materias que no tienen prerrequisito
    public List<Materia> getMateriasSinPrerrequisito() {
        return materiaRepository.findMateriasSinPrerrequisito();
    }

    // Obtener materias con cupo disponible
    public List<Materia> getMateriasConCupoDisponible() {
        return materiaRepository.findMateriasConCupoDisponible();
    }

    // Contar cuántas materias tiene un programa
    public long countMateriasByProgramaId(Long programaId) {
        return materiaRepository.countMateriasByProgramaId(programaId);
    }

    // Crear una nueva materia
    public Materia createMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    // Actualizar una materia
    public Materia updateMateria(Long id, Materia materiaDetails) {
        return materiaRepository.findById(id).map(materia -> {
            materia.setNombre(materiaDetails.getNombre());
            materia.setPrograma(materiaDetails.getPrograma());
            materia.setSemestre(materiaDetails.getSemestre());
            materia.setElectiva(materiaDetails.isElectiva());
            materia.setPrerrequisito(materiaDetails.getPrerrequisito());
            materia.setCupoMaximo(materiaDetails.getCupoMaximo());
            return materiaRepository.save(materia);
        }).orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    // Eliminar una materia
    public void deleteMateria(Long id) {
        if (!materiaRepository.existsById(id)) {
            throw new RuntimeException("Materia no encontrada");
        }
        materiaRepository.deleteById(id);
    }
}
