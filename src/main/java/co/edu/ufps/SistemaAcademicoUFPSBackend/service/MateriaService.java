package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Materia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.MateriaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ProgramaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    // Obtener todas las materias
    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    // Obtener una materia por ID
    public Optional<Materia> getMateriaById(Long id) {
        return materiaRepository.findById(id);
    }

    @Transactional
    public Materia createMateria(Materia materia) {
        // Obtener el Programa y el Semestre existentes desde la BD
        Programa programa = programaRepository.findById(materia.getPrograma().getId())
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));

        Semestre semestre = semestreRepository.findById(materia.getSemestre().getId())
                .orElseThrow(() -> new RuntimeException("Semestre no encontrado"));

        // Asignar las entidades gestionadas a la materia
        materia.setPrograma(programa);
        materia.setSemestre(semestre);

        // Guardar la materia
        return materiaRepository.save(materia);
    }

    // Actualizar una materia
    public Materia updateMateria(Long id, Materia materiaDetails) {
        return materiaRepository.findById(id).map(materia -> {
            materia.setNombre(materiaDetails.getNombre());
            materia.setEstado(materiaDetails.getEstado());
            materia.setSemestre(materiaDetails.getSemestre());
            materia.setPrograma(materiaDetails.getPrograma());
            materia.setElectiva(materiaDetails.isElectiva());
            materia.setAsignaturas(materiaDetails.getAsignaturas());
            materia.setPrerrequisito(materiaDetails.getPrerrequisito());
            materia.setContenido(materiaDetails.getContenido());
            materia.setObjetivos(materiaDetails.getObjetivos());
            materia.setCompetencias(materiaDetails.getCompetencias());
            materia.setCupoMaximo(materiaDetails.getCupoMaximo());
            materia.setCreditos(materiaDetails.getCreditos());
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

    // Métodos adicionales para búsquedas específicas:
    public Optional<Materia> getMateriaByNombre(String nombre) {
        return materiaRepository.findByNombre(nombre);
    }

    public List<Materia> getMateriasByProgramaId(Long programaId) {
        return materiaRepository.findByProgramaId(programaId);
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
