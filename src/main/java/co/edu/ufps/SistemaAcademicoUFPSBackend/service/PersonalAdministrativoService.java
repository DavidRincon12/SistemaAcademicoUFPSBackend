package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PersonalAdministrativo;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonalAdministrativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAdministrativoService {

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<PersonalAdministrativo> getAllPersonalAdministrativo() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<PersonalAdministrativo> getPersonalAdministrativoById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public PersonalAdministrativo createPersonalAdministrativo(PersonalAdministrativo personalAdministrativo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public PersonalAdministrativo updatePersonalAdministrativo(Long id, PersonalAdministrativo personalAdministrativoDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deletePersonalAdministrativo(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ------------------------- Consultas Específicas -------------------------
    @Transactional(readOnly = true)
    public List<PersonalAdministrativo> getPersonalAdministrativoByCargo(String cargo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<PersonalAdministrativo> getPersonalAdministrativoByDepartamento(String departamento) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public List<PersonalAdministrativo> getPersonalAdministrativoByAreaTrabajo(String areaTrabajo) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<PersonalAdministrativo> getPersonalAdministrativoByPersonaId(Long personaId) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}