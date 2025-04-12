package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Docente;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HorarioAsesoria;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.DocenteRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HorarioAsesoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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

    public HorarioAsesoria createHorarioAsesoria(HorarioAsesoria horarioAsesoria) {
        Docente docente = docenteRepository.findById(horarioAsesoria.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        List<HorarioAsesoria> existentes = horarioAsesoriaRepository.findByDiaSemana(horarioAsesoria.getDiaSemana());
        for (HorarioAsesoria existente : existentes) {
            if (existente.getDocente().getId().equals(docente.getId()) &&
                    seSolapan(horarioAsesoria.getHoraInicio(), horarioAsesoria.getHoraFin(),
                            existente.getHoraInicio(), existente.getHoraFin())) {
                throw new RuntimeException("Conflicto con otro horario de asesoría del docente.");
            }
        }

        horarioAsesoria.setDocente(docente);
        return horarioAsesoriaRepository.save(horarioAsesoria);
    }

    public HorarioAsesoria updateHorarioAsesoria(Long id, HorarioAsesoria actualizado) {
        HorarioAsesoria horario = horarioAsesoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        Docente docente = docenteRepository.findById(actualizado.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

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
        horario.setDocente(docente);

        return horarioAsesoriaRepository.save(horario);
    }

    public void deleteHorarioAsesoria(Long id) {
        if (!horarioAsesoriaRepository.existsById(id)) {
            throw new RuntimeException("Horario de asesoría no encontrado");
        }
        horarioAsesoriaRepository.deleteById(id);
    }

    private boolean seSolapan(LocalTime inicio1, LocalTime fin1, LocalTime inicio2, LocalTime fin2) {
        return inicio1.isBefore(fin2) && fin1.isAfter(inicio2);
    }
}
