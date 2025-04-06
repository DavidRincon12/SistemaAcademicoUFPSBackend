package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PersonalAdministrativo;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonalAdministrativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAdministrativoService {

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;
    @Autowired
    private PersonaRepository personaRepository;

    public List<PersonalAdministrativo> obtenerTodos() {
        return personalAdministrativoRepository.findAll();
    }

    public Optional<PersonalAdministrativo> obtenerPorId(Long id) {
        return personalAdministrativoRepository.findById(id);
    }

    public List<PersonalAdministrativo> obtenerPorCargo(String cargo) {
        return personalAdministrativoRepository.findByCargo(cargo);
    }

    public List<PersonalAdministrativo> obtenerPorDepartamento(String departamento) {
        return personalAdministrativoRepository.findByDepartamento(departamento);
    }

    public List<PersonalAdministrativo> obtenerPorAreaTrabajo(String areaTrabajo) {
        return personalAdministrativoRepository.findByAreaTrabajo(areaTrabajo);
    }

    public Optional<PersonalAdministrativo> obtenerPorPersonaId(Long personaId) {
        return personalAdministrativoRepository.findByPersonaId(personaId);
    }

public PersonalAdministrativo createPersonalAdministrativo(PersonalAdministrativo personalAdministrativo) {
    if (personalAdministrativo.getPersona() != null && personalAdministrativo.getPersona().getId() != null) {
        Persona persona = personaRepository.findById(personalAdministrativo.getPersona().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Persona no encontrada con id: " + personalAdministrativo.getPersona().getId()));
        personalAdministrativo.setPersona(persona);
    }
    return personalAdministrativoRepository.save(personalAdministrativo);
}

    

    public PersonalAdministrativo actualizar(Long id, PersonalAdministrativo actualizado) {
        return personalAdministrativoRepository.findById(id).map(pa -> {
            pa.setCargo(actualizado.getCargo());
            pa.setDepartamento(actualizado.getDepartamento());
            pa.setAreaTrabajo(actualizado.getAreaTrabajo());
            pa.setFechaContratacion(actualizado.getFechaContratacion());
            pa.setPersona(actualizado.getPersona());
            return personalAdministrativoRepository.save(pa);
        }).orElseThrow(() -> new RuntimeException("Personal administrativo no encontrado"));
    }

    public void eliminar(Long id) {
        if (!personalAdministrativoRepository.existsById(id)) {
            throw new RuntimeException("Personal administrativo no encontrado");
        }
        personalAdministrativoRepository.deleteById(id);
    }
}
