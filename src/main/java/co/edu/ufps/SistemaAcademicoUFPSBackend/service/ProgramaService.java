package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ProgramaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaService {
    @Autowired
    private ProgramaRepository programaRepository;
    @Autowired
    private FacultadRepository facultadRepository;
    @Autowired
    private DocenteRepository docenteRepository;

    // Obtener todos los programas
    // Obtener todos los programas
    public List<Programa> getAllProgramas() {
        return programaRepository.findAll();
    }

    // Obtener un programa por ID
    public Optional<Programa> getProgramaById(Long id) {
        return programaRepository.findById(id);
    }

    // Crear un nuevo programa
    public Programa createPrograma(Programa programa) {
        // Verificar que el director asociado exista
        if (programa.getDirector() == null || programa.getDirector().getId() == null) {
            throw new RuntimeException("El director del programa es obligatorio");
        }

        // Verificar que la facultad asociada exista
        if (programa.getFacultad() == null || programa.getFacultad().getId() == null) {
            throw new RuntimeException("La facultad del programa es obligatoria");
        }

        // Validar que el director exista en la base de datos
        Docente director = docenteRepository.findById(programa.getDirector().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Director no encontrado con id: " + programa.getDirector().getId()));
        programa.setDirector(director);

        // Validar que la facultad exista en la base de datos
        Facultad facultad = facultadRepository.findById(programa.getFacultad().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Facultad no encontrada con id: " + programa.getFacultad().getId()));
        programa.setFacultad(facultad);

        // Guardar el programa
        return programaRepository.save(programa);
    }

    // Actualizar un programa existente
    public Programa updatePrograma(Long id, Programa programaDetails) {
        return programaRepository.findById(id).map(programa -> {
            programa.setNombre(programaDetails.getNombre());
            programa.setCodigo(programaDetails.getCodigo());
            programa.setDuracion(programaDetails.getDuracion());
            programa.setRegistroSnies(programaDetails.getRegistroSnies());
            programa.setDirector(programaDetails.getDirector());
            programa.setFacultad(programaDetails.getFacultad());
            return programaRepository.save(programa);
        }).orElseThrow(() -> new RuntimeException("Programa no encontrado con id: " + id));
    }

    // Eliminar un programa
    public void deletePrograma(Long id) {
        if (!programaRepository.existsById(id)) {
            throw new RuntimeException("Programa no encontrado con id: " + id);
        }
        programaRepository.deleteById(id);
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