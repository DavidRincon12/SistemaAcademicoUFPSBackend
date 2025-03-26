package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.ReservaRecurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ReservaRecursoRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaRecursoServiceImpl implements ReservaRecursoService {

    private final ReservaRecursoRepository reservaRecursoRepository;

    public ReservaRecursoServiceImpl(ReservaRecursoRepository reservaRecursoRepository) {
        this.reservaRecursoRepository = reservaRecursoRepository;
    }

    @Override
    public ReservaRecurso save(ReservaRecurso reservaRecurso) {
        return reservaRecursoRepository.save(reservaRecurso);
    }

    @Override
    public ReservaRecurso update(ReservaRecurso reservaRecurso) {
        // Se pueden agregar validaciones o lógica adicional antes de actualizar
        return reservaRecursoRepository.save(reservaRecurso);
    }

    @Override
    public void delete(Long id) {
        reservaRecursoRepository.deleteById(id);
    }

    @Override
    public Optional<ReservaRecurso> findById(Long id) {
        return reservaRecursoRepository.findById(id);
    }

    @Override
    public List<ReservaRecurso> findAll() {
        return reservaRecursoRepository.findAll();
    }

    @Override
    public List<ReservaRecurso> findByEstado(String estado) {
        return reservaRecursoRepository.findByEstado(estado);
    }

    @Override
    public List<ReservaRecurso> findByFechaInicioBeforeAndFechaFinAfter(Date fechaInicio, Date fechaFin) {
        return reservaRecursoRepository.findByFechaInicioBeforeAndFechaFinAfter(fechaInicio, fechaFin);
    }
}
