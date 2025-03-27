package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PersonalAdministrativo;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonalAdministrativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAdministrativoService {

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    // Obtener todos los empleados administrativos
    public List<PersonalAdministrativo> getAllPersonalAdministrativo() {
        return personalAdministrativoRepository.findAll();
    }

    // Obtener un administrativo por ID
    public Optional<PersonalAdministrativo> getPersonalAdministrativoById(Long id) {
        return personalAdministrativoRepository.findById(id);
    }

    // Obtener administrativos por cargo
    public List<PersonalAdministrativo> getPersonalAdministrativoByCargo(String cargo) {
        return personalAdministrativoRepository.findByCargo(cargo);
    }

    // Obtener administrativos por departamento
    public List<PersonalAdministrativo> getPersonalAdministrativoByDepartamento(String departamento) {
        return personalAdministrativoRepository.findByDepartamento(departamento);
    }

    // Obtener administrativos por área de trabajo
    public List<PersonalAdministrativo> getPersonalAdministrativoByAreaTrabajo(String areaTrabajo) {
        return personalAdministrativoRepository.findByAreaTrabajo(areaTrabajo);
    }

    // Obtener administrativo por persona asociada
    public Optional<PersonalAdministrativo> getPersonalAdministrativoByPersonaId(Long personaId) {
        return personalAdministrativoRepository.findByPersonaId(personaId);
    }

    // Crear un nuevo administrativo
    public PersonalAdministrativo createPersonalAdministrativo(PersonalAdministrativo personalAdministrativo) {
        return personalAdministrativoRepository.save(personalAdministrativo);
    }

    // Actualizar un administrativo
    public PersonalAdministrativo updatePersonalAdministrativo(Long id, PersonalAdministrativo personalAdministrativoDetails) {
        return personalAdministrativoRepository.findById(id).map(personalAdministrativo -> {
            personalAdministrativo.setCargo(personalAdministrativoDetails.getCargo());
            return personalAdministrativoRepository.save(personalAdministrativo);
        }).orElseThrow(() -> new RuntimeException("Personal Administrativo no encontrado"));
    }

    // Eliminar un administrativo
    public void deletePersonalAdministrativo(Long id) {
        if (!personalAdministrativoRepository.existsById(id)) {
            throw new RuntimeException("Personal Administrativo no encontrado");
        }
        personalAdministrativoRepository.deleteById(id);
    }
}
