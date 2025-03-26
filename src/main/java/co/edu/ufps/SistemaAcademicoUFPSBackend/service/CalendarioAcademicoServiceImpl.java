package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.CalendarioAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.CalendarioAcademicoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioAcademicoServiceImpl implements CalendarioAcademicoService {

    private final CalendarioAcademicoRepository calendarioAcademicoRepository;

    public CalendarioAcademicoServiceImpl(CalendarioAcademicoRepository calendarioAcademicoRepository) {
        this.calendarioAcademicoRepository = calendarioAcademicoRepository;
    }

    @Override
    public CalendarioAcademico save(CalendarioAcademico calendarioAcademico) {
        return calendarioAcademicoRepository.save(calendarioAcademico);
    }

    @Override
    public CalendarioAcademico update(CalendarioAcademico calendarioAcademico) {
        // Aquí puedes agregar lógica adicional, por ejemplo, validar cambios antes de actualizar
        return calendarioAcademicoRepository.save(calendarioAcademico);
    }

    @Override
    public void delete(Long id) {
        calendarioAcademicoRepository.deleteById(id);
    }

    @Override
    public Optional<CalendarioAcademico> findById(Long id) {
        return calendarioAcademicoRepository.findById(id);
    }

    @Override
    public List<CalendarioAcademico> findAll() {
        return calendarioAcademicoRepository.findAll();
    }

    @Override
    public Optional<CalendarioAcademico> findByNombrePeriodo(String nombrePeriodo) {
        return calendarioAcademicoRepository.findByNombrePeriodo(nombrePeriodo);
    }

    @Override
    public List<CalendarioAcademico> findByFechaDentroDelPeriodo(Date fecha) {
        return calendarioAcademicoRepository.findByFechaDentroDelPeriodo(fecha);
    }

    @Override
    public List<CalendarioAcademico> findByPeriodoEntreFechas(Date fechaInicio, Date fechaFin) {
        return calendarioAcademicoRepository.findByPeriodoEntreFechas(fechaInicio, fechaFin);
    }

    @Override
    public Optional<CalendarioAcademico> findCalendarioActivo() {
        return calendarioAcademicoRepository.findCalendarioActivo();
    }
}
