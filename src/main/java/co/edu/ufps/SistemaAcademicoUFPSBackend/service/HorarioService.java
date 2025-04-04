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

    // Obtener todos los horarios

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    // Obtener un horario por ID
    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    // Crear un nuevo horario
    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    // Actualizar un horario
    public Horario updateHorario(Long id, Horario horarioDetails) {
        return horarioRepository.findById(id).map(horario -> {
            horario.setDia(horarioDetails.getDia());
            horario.setHoraInicio(horarioDetails.getHoraInicio());
            horario.setHoraFin(horarioDetails.getHoraFin());
            return horarioRepository.save(horario);
        }).orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    // Eliminar un horario
    public void deleteHorario(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new RuntimeException("Horario no encontrado");
        }
        horarioRepository.deleteById(id);
    }
}