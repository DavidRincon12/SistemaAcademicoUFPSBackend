package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Semestre;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.SemestreRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SemestreServiceImpl implements SemestreService {

    private final SemestreRepository semestreRepository;

    public SemestreServiceImpl(SemestreRepository semestreRepository) {
        this.semestreRepository = semestreRepository;
    }

    @Override
    public Semestre save(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    @Override
    public Semestre update(Semestre semestre) {
        // Aquí se pueden agregar validaciones o lógica adicional antes de actualizar
        return semestreRepository.save(semestre);
    }

    @Override
    public void delete(Long id) {
        semestreRepository.deleteById(id);
    }

    @Override
    public Optional<Semestre> findById(Long id) {
        return semestreRepository.findById(id);
    }

    @Override
    public List<Semestre> findAll() {
        return semestreRepository.findAll();
    }

    @Override
    public Optional<Semestre> findByNombre(String nombre) {
        return semestreRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Semestre> findSemestreActual(Date fechaInicio, Date fechaFin) {
        // Se delega la consulta al repository, utilizando el rango de fechas que se espera que contenga la fecha actual.
        return semestreRepository.findByFechaInicioBeforeAndFechaFinAfter(fechaInicio, fechaFin);
    }
}
