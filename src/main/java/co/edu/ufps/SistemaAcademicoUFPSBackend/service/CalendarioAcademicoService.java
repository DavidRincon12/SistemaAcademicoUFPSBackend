package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.CalendarioAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.CalendarioAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioAcademicoService {

    @Autowired
    private CalendarioAcademicoRepository calendarioAcademicoRepository;

    // Obtener todos los calendarios académicos
    public List<CalendarioAcademico> getAllCalendarios() {
        return calendarioAcademicoRepository.findAll();
    }

    // Obtener un calendario por ID
    public Optional<CalendarioAcademico> getCalendarioById(Long id) {
        return calendarioAcademicoRepository.findById(id);
    }

    // Crear un nuevo calendario académico
    public CalendarioAcademico createCalendario(CalendarioAcademico calendario) {
        return calendarioAcademicoRepository.save(calendario);
    }

    // Actualizar un calendario académico
    public CalendarioAcademico updateCalendario(Long id, CalendarioAcademico calendarioDetails) {
        return calendarioAcademicoRepository.findById(id).map(calendario -> {
            calendario.setNombrePeriodo(calendarioDetails.getNombrePeriodo());
            calendario.setFechaInicio(calendarioDetails.getFechaInicio());
            calendario.setFechaFin(calendarioDetails.getFechaFin());
            return calendarioAcademicoRepository.save(calendario);
        }).orElseThrow(() -> new RuntimeException("Calendario no encontrado"));
    }

    // Eliminar un calendario académico
    public void deleteCalendario(Long id) {
        if (!calendarioAcademicoRepository.existsById(id)) {
            throw new RuntimeException("Calendario no encontrado");
        }
        calendarioAcademicoRepository.deleteById(id);
    }

    // Métodos adicionales requeridos

    public Optional<CalendarioAcademico> findByNombrePeriodo(String nombrePeriodo) {
        return calendarioAcademicoRepository.findByNombrePeriodo(nombrePeriodo);
    }

    public List<CalendarioAcademico> findByFechaDentroDelPeriodo(Date fecha) {
        return calendarioAcademicoRepository.findByFechaDentroDelPeriodo(fecha);
    }

    public List<CalendarioAcademico> findByPeriodoEntreFechas(Date fechaInicio, Date fechaFin) {
        return calendarioAcademicoRepository.findByPeriodoEntreFechas(fechaInicio, fechaFin);
    }

    public Optional<CalendarioAcademico> findCalendarioActivo() {
        return calendarioAcademicoRepository.findCalendarioActivo();
    }
}
