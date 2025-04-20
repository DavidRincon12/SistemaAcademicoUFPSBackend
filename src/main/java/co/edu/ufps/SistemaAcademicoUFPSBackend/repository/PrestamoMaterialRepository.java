package co.edu.ufps.SistemaAcademicoUFPSBackend.repository;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.PrestamoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoMaterialRepository extends JpaRepository<PrestamoMaterial, Long> {
    List<PrestamoMaterial> findByEstado(String estado);
    List<PrestamoMaterial> findByRecurso_Id(Long recursoId);
}