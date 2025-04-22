package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PrestamoMaterial;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.RecursoAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PrestamoMaterialRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.RecursoAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PrestamoMaterialService {

    @Autowired
    private PrestamoMaterialRepository prestamoRepo;

    @Autowired
    private RecursoAcademicoRepository recursoRepo;

    public List<PrestamoMaterial> getAllPrestamos() {
        return prestamoRepo.findAll();
    }

    public PrestamoMaterial getPrestamoById(Long id) {
        return prestamoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Transactional
    public PrestamoMaterial crearPrestamo(PrestamoMaterial prestamo) {
        RecursoAcademico recurso = recursoRepo.findById(prestamo.getRecurso().getId())
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + prestamo.getRecurso().getId()));
        if (!recurso.isDisponible() || recurso.isEnMantenimiento()) {
            throw new RuntimeException("Recurso no disponible para préstamo");
        }
        recurso.setDisponible(false);
        recursoRepo.save(recurso);
        prestamo.setEstado("PRESTADO");
        prestamo.setFechaPrestamo(new Date());
        return prestamoRepo.save(prestamo);
    }

    @Transactional
    public PrestamoMaterial devolverPrestamo(Long id) {
        PrestamoMaterial p = getPrestamoById(id);
        if (!p.getEstado().equals("PRESTADO")) {
            throw new RuntimeException("El préstamo no está activo");
        }
        p.setEstado("DEVUELTO");
        p.setFechaDevolucionReal(new Date());
        RecursoAcademico recurso = p.getRecurso();
        recurso.setDisponible(true);
        recursoRepo.save(recurso);
        return prestamoRepo.save(p);
    }

    @Transactional
    public PrestamoMaterial reportarMantenimiento(Long id) {
        PrestamoMaterial p = getPrestamoById(id);
        p.setEstado("EN_MANTENIMIENTO");
        p.setFechaDevolucionReal(new Date());
        RecursoAcademico recurso = p.getRecurso();
        recurso.setEnMantenimiento(true);
        recursoRepo.save(recurso);
        return prestamoRepo.save(p);
    }

    public List<PrestamoMaterial> getPrestamosByEstado(String estado) {
        return prestamoRepo.findByEstado(estado);
    }

    public List<PrestamoMaterial> getPrestamosByRecurso(Long recursoId) {
        return prestamoRepo.findByRecurso_Id(recursoId);
    }
}