package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Programa;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    // Obtener todos los programas
    public List<Programa> getAllProgramas() {
        return programaRepository.findAll();
    }

    // Obtener un programa por ID
    public Optional<Programa> getProgramaById(Long id) {
        return programaRepository.findById(id);
    }

    // Obtener un programa por nombre
    public Optional<Programa> getProgramaByNombre(String nombre) {
        return programaRepository.findByNombre(nombre);
    }

    // Obtener un programa por código
    public Optional<Programa> getProgramaByCodigo(String codigo) {
        return programaRepository.findByCodigo(codigo);
    }

    // Obtener programas de una facultad
    public List<Programa> getProgramasByFacultadId(Long facultadId) {
        return programaRepository.findByFacultadId(facultadId);
    }

    // Crear un nuevo programa
    public Programa createPrograma(Programa programa) {
        return programaRepository.save(programa);
    }

    // Actualizar un programa
    public Programa updatePrograma(Long id, Programa programaDetails) {
        return programaRepository.findById(id).map(programa -> {
            programa.setNombre(programaDetails.getNombre());
            programa.setCodigo(programaDetails.getCodigo());
            programa.setDuracion(programaDetails.getDuracion());
            programa.setDirector(programaDetails.getDirector());
            programa.setRegistroSnies(programaDetails.getRegistroSnies());
            programa.setFacultad(programaDetails.getFacultad());
            return programaRepository.save(programa);
        }).orElseThrow(() -> new RuntimeException("Programa no encontrado"));
    }

    // Eliminar un programa
    public void deletePrograma(Long id) {
        if (!programaRepository.existsById(id)) {
            throw new RuntimeException("Programa no encontrado");
        }
        programaRepository.deleteById(id);
    }
}
