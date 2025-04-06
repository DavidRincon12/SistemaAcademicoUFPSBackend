package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Facultad;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadService {
    @Autowired
    private FacultadRepository facultadRepository;
    @Autowired
    private DocenteRepository docenteRepository;

    // Obtener todas las facultades

    public List<Facultad> getAllFacultades() {
        return facultadRepository.findAll();
    }

    // Obtener una facultad por ID
    public Optional<Facultad> getFacultadById(Long id) {
        return facultadRepository.findById(id);
    }

    // Crear una nueva facultad
    public Facultad createFacultad(Facultad facultad) {
        // Verificar que el decano asociado exista
        if (facultad.getDecano() == null || facultad.getDecano().getId() == null) {
            throw new RuntimeException("El decano de la facultad es obligatorio");
        }

        Docente decano = docenteRepository.findById(facultad.getDecano().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Decano no encontrado con id: " + facultad.getDecano().getId()));
        facultad.setDecano(decano);

        // Guardar la facultad
        return facultadRepository.save(facultad);
    }

    // Actualizar facultad
    public Facultad updateFacultad(Long id, Facultad facultadDetails) {
        return facultadRepository.findById(id).map(facultad -> {
            facultad.setNombre(facultadDetails.getNombre());
            facultad.setDecano(facultadDetails.getDecano());
            return facultadRepository.save(facultad);
        }).orElseThrow(() -> new RuntimeException("Facultad no encontrada"));
    }

    // Eliminar facultad
    public void deleteFacultad(Long id) {
        if (!facultadRepository.existsById(id)) {
            throw new RuntimeException("Facultad no encontrada");
        }
        facultadRepository.deleteById(id);
    }

    // Buscar facultad por nombre (sin importar mayúsculas/minúsculas)

    public Optional<Facultad> getFacultadByNombre(String nombre) {
        return facultadRepository.findByNombreIgnoreCase(nombre);
    }

    // Obtener facultades con decano asignado
    public List<Facultad> getFacultadesConDecano() {
        return facultadRepository.findFacultadesConDecano();
    }

    // Buscar facultad por ID del decano
    public Optional<Facultad> getFacultadByDecanoId(Long decanoId) {
        return facultadRepository.findByDecanoId(decanoId);
    }

    // Buscar facultades por nombre con coincidencia parcial
    public List<Facultad> searchFacultadesByNombre(String nombre) {
        return facultadRepository.searchByNombre(nombre);
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