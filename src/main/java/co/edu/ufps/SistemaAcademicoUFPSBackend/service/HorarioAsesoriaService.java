package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioAsesoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioAsesoriaService {

    @Autowired
    private HorarioAsesoriaRepository horarioAsesoriaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    public List<HorarioAsesoria> getAllHorariosAsesoria() {
        return horarioAsesoriaRepository.findAll();
    }

    public Optional<HorarioAsesoria> getHorarioAsesoriaById(Long id) {
        return horarioAsesoriaRepository.findById(id);
    }

    @Transactional
    public HorarioAsesoria createHorarioAsesoria(HorarioAsesoria horario) {
        Docente docente = docenteRepository.findById(horario.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        // Validar que no se solapen horarios
        List<HorarioAsesoria> existentes = horarioAsesoriaRepository.findByDiaSemana(horario.getDiaSemana());
        for (HorarioAsesoria existente : existentes) {
            if (existente.getDocente().getId().equals(docente.getId()) &&
                    seSolapan(horario.getHoraInicio(), horario.getHoraFin(),
                            existente.getHoraInicio(), existente.getHoraFin())) {
                throw new RuntimeException("Ya existe una asesoría para este docente en ese horario.");
            }
        }

        horario.setDocente(docente);
        return horarioAsesoriaRepository.save(horario);
    }

    @Transactional
    public HorarioAsesoria updateHorarioAsesoria(Long id, HorarioAsesoria actualizado) {
        HorarioAsesoria horario = horarioAsesoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        Docente docente = docenteRepository.findById(actualizado.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        // Validar que no se solapen con otros horarios del mismo docente
        List<HorarioAsesoria> existentes = horarioAsesoriaRepository.findByDiaSemana(actualizado.getDiaSemana());
        for (HorarioAsesoria existente : existentes) {
            if (!existente.getId().equals(id) &&
                    existente.getDocente().getId().equals(docente.getId()) &&
                    seSolapan(actualizado.getHoraInicio(), actualizado.getHoraFin(),
                            existente.getHoraInicio(), existente.getHoraFin())) {
                throw new RuntimeException("Conflicto al actualizar: ya existe una asesoría para el docente en ese horario.");
            }
        }

        horario.setDiaSemana(actualizado.getDiaSemana());
        horario.setHoraInicio(actualizado.getHoraInicio());
        horario.setHoraFin(actualizado.getHoraFin());
        horario.setLugar(actualizado.getLugar()); // ✅ Actualiza el nuevo campo
        horario.setDocente(docente);

        return horarioAsesoriaRepository.save(horario);
    }

    public void deleteHorarioAsesoria(Long id) {
        if (!horarioAsesoriaRepository.existsById(id)) {
            throw new RuntimeException("Horario no encontrado");
        }
        horarioAsesoriaRepository.deleteById(id);
    }

    public List<HorarioAsesoria> getHorariosByDiaSemana(DayOfWeek diaSemana) {
        return horarioAsesoriaRepository.findByDiaSemana(diaSemana);
    }

    private boolean seSolapan(LocalTime inicio1, LocalTime fin1, LocalTime inicio2, LocalTime fin2) {
        return !inicio1.isAfter(fin2) && !fin1.isBefore(inicio2);
    }
}
