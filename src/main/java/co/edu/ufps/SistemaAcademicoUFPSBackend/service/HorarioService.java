package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asignatura;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Horario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsignaturaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    public Horario createHorario(Horario horario) {
        Asignatura asignatura = asignaturaRepository.findById(horario.getAsignatura().getId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        // Validar que no se solapen horarios
        List<Horario> horariosExistentes = horarioRepository.findByDia(horario.getDia());
        for (Horario existente : horariosExistentes) {
            if (seSolapan(horario.getHoraInicio(), horario.getHoraFin(), existente.getHoraInicio(), existente.getHoraFin())) {
                throw new RuntimeException("Conflicto de horario detectado para el día " + horario.getDia());
            }
        }

        horario.setAsignatura(asignatura);
        return horarioRepository.save(horario);
    }

    public Horario updateHorario(Long id, Horario horarioActualizado) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(horarioActualizado.getAsignatura().getId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        // Validación de solapamiento excluyendo el actual
        List<Horario> horariosExistentes = horarioRepository.findByDia(horarioActualizado.getDia());
        for (Horario existente : horariosExistentes) {
            if (!existente.getId().equals(id) &&
                    seSolapan(horarioActualizado.getHoraInicio(), horarioActualizado.getHoraFin(),
                            existente.getHoraInicio(), existente.getHoraFin())) {
                throw new RuntimeException("Conflicto de horario al actualizar.");
            }
        }

        horario.setDia(horarioActualizado.getDia());
        horario.setHoraInicio(horarioActualizado.getHoraInicio());
        horario.setHoraFin(horarioActualizado.getHoraFin());
        horario.setAsignatura(asignatura);

        return horarioRepository.save(horario);
    }

    public void deleteHorario(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new RuntimeException("Horario no encontrado");
        }
        horarioRepository.deleteById(id);
    }

    private boolean seSolapan(Date inicio1, Date fin1, Date inicio2, Date fin2) {
        return inicio1.before(fin2) && fin1.after(inicio2);
    }
}
