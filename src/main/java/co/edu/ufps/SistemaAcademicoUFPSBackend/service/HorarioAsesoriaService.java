package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioAsesoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorarioAsesoriaService {

    @Autowired
    private final HorarioAsesoriaRepository horarioAsesoriaRepository;
    
    // Obtener horarios de asesoría disponibles para un día y hora específicos
    public List<HorarioAsesoria> getHorariosDisponibles(DayOfWeek diaSemana, LocalTime hora) {
        return horarioAsesoriaRepository.findByDiaSemanaAndHora(diaSemana, hora);
    }

    // Obtener todos los horarios de asesoría
    public List<HorarioAsesoria> getAllHorariosAsesoria() {
        return horarioAsesoriaRepository.findAll();
    }

    // Obtener un horario de asesoría por ID
    public Optional<HorarioAsesoria> getHorarioAsesoriaById(Long id) {
        return horarioAsesoriaRepository.findById(id);
    }

    // Crear un nuevo horario de asesoría
    public HorarioAsesoria createHorarioAsesoria(HorarioAsesoria horarioAsesoria) {
        return horarioAsesoriaRepository.save(horarioAsesoria);
    }

    // Actualizar un horario de asesoría existente
    public HorarioAsesoria updateHorarioAsesoria(Long id, HorarioAsesoria horarioAsesoriaDetails) {
        return horarioAsesoriaRepository.findById(id).map(horarioAsesoria -> {
            horarioAsesoria.setDiaSemana((horarioAsesoriaDetails.getDiaSemana()));
            horarioAsesoria.setHoraInicio(horarioAsesoriaDetails.getHoraInicio());
            horarioAsesoria.setHoraFin(horarioAsesoriaDetails.getHoraFin());
            horarioAsesoria.setDocente(horarioAsesoriaDetails.getDocente());
            return horarioAsesoriaRepository.save(horarioAsesoria);
        }).orElseThrow(() -> new RuntimeException("Horario de asesoría no encontrado"));
    }

    // Eliminar un horario de asesoría
    public void deleteHorarioAsesoria(Long id) {
        if (!horarioAsesoriaRepository.existsById(id)) {
            throw new RuntimeException("Horario de asesoría no encontrado");
        }
        horarioAsesoriaRepository.deleteById(id);
    }
}