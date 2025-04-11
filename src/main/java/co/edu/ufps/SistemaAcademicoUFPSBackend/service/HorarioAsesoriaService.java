package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Horario;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Examen;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ExamenRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioAsesoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorarioAsesoriaService {

    @Autowired
    private final HorarioAsesoriaRepository horarioAsesoriaRepository;

    @Autowired
    private final HorarioRepository horarioRepository;

    @Autowired
    private final ExamenRepository examenRepository;

    public List<HorarioAsesoria> getHorariosDisponibles(DayOfWeek diaSemana, LocalTime hora) {
        return horarioAsesoriaRepository.findByDiaSemanaAndHora(diaSemana, hora);
    }

    public List<HorarioAsesoria> getAllHorariosAsesoria() {
        return horarioAsesoriaRepository.findAll();
    }

    public Optional<HorarioAsesoria> getHorarioAsesoriaById(Long id) {
        return horarioAsesoriaRepository.findById(id);
    }

    public HorarioAsesoria createHorarioAsesoria(HorarioAsesoria horarioAsesoria) {
        // Validación de solapamiento con clases
        List<Horario> clasesSolapadas = horarioRepository.findByDiaSemanaAndHoraInicioAndHoraFin(
                horarioAsesoria.getDiaSemana().name(),
                java.sql.Time.valueOf(horarioAsesoria.getHoraInicio()),
                java.sql.Time.valueOf(horarioAsesoria.getHoraFin()));

        if (!clasesSolapadas.isEmpty()) {
            throw new RuntimeException("El docente tiene clases en ese horario.");
        }

        // Validación de solapamiento con exámenes
        List<Examen> examenes = examenRepository.findByFecha(new Date());
        for (Examen e : examenes) {
            if (e.getAsignatura().getDocente().getId().equals(horarioAsesoria.getDocente().getId()) &&
                    e.getHoraInicio().toInstant().isBefore(java.sql.Time.valueOf(horarioAsesoria.getHoraFin()).toInstant()) &&
                    e.getHoraFin().toInstant().isAfter(java.sql.Time.valueOf(horarioAsesoria.getHoraInicio()).toInstant())) {
                throw new RuntimeException("El docente tiene exámenes en ese horario.");
            }
        }

        return horarioAsesoriaRepository.save(horarioAsesoria);
    }

    public HorarioAsesoria updateHorarioAsesoria(Long id, HorarioAsesoria horarioAsesoriaDetails) {
        return horarioAsesoriaRepository.findById(id).map(horarioAsesoria -> {
            horarioAsesoria.setDiaSemana(horarioAsesoriaDetails.getDiaSemana());
            horarioAsesoria.setHoraInicio(horarioAsesoriaDetails.getHoraInicio());
            horarioAsesoria.setHoraFin(horarioAsesoriaDetails.getHoraFin());
            horarioAsesoria.setDocente(horarioAsesoriaDetails.getDocente());
            return horarioAsesoriaRepository.save(horarioAsesoria);
        }).orElseThrow(() -> new RuntimeException("Horario de asesoría no encontrado"));
    }

    public void deleteHorarioAsesoria(Long id) {
        if (!horarioAsesoriaRepository.existsById(id)) {
            throw new RuntimeException("Horario de asesoría no encontrado");
        }
        horarioAsesoriaRepository.deleteById(id);
    }
}
