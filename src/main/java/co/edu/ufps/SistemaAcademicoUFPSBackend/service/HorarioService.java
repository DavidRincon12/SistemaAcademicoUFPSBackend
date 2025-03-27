package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Horario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // ------------------------- CRUD Básico -------------------------
    @Transactional(readOnly = true)
    public List<Horario> getAllHorarios() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public Optional<Horario> getHorarioById(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Horario createHorario(Horario horario) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public Horario updateHorario(Long id, Horario horarioDetails) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional
    public void deleteHorario(Long id) {
        throw new UnsupportedOperationException("Método no implementado");
    }
}