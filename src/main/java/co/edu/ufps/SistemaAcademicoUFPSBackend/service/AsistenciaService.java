package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteAsistenciaDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Asistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Clase;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoAsistencia;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.AsistenciaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.ClaseRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.EstudianteRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ClaseRepository claseRepository;

    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id);
    }

    public Asistencia createAsistencia(Asistencia asistencia) {
        Estudiante estudiante = estudianteRepository.findById(asistencia.getEstudiante().getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Clase clase = claseRepository.findById(asistencia.getClase().getId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        asistencia.setEstudiante(estudiante);
        asistencia.setClase(clase);
        if (asistencia.getEstado() == null) {
            asistencia.setEstado(EstadoAsistencia.AUSENTE); // Estado por defecto si no se especifica
        }

        return asistenciaRepository.save(asistencia);
    }

    public Asistencia updateAsistencia(Long id, Asistencia asistenciaDetails) {
        return asistenciaRepository.findById(id).map(asistencia -> {
            Estudiante estudiante = estudianteRepository.findById(asistenciaDetails.getEstudiante().getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            Clase clase = claseRepository.findById(asistenciaDetails.getClase().getId())
                    .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

            asistencia.setEstudiante(estudiante);
            asistencia.setClase(clase);
            asistencia.setEstado(asistenciaDetails.getEstado());

            return asistenciaRepository.save(asistencia);
        }).orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
    }

    public void deleteAsistencia(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new RuntimeException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }

    public List<Asistencia> findByEstudianteId(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId);
    }

    public List<Asistencia> findByClaseId(Long claseId) {
        return asistenciaRepository.findByClaseId(claseId);
    }

    public List<Asistencia> findByEstudianteIdAndClaseId(Long estudianteId, Long claseId) {
        return asistenciaRepository.findByEstudianteIdAndClaseId(estudianteId, claseId);
    }

    public List<Asistencia> findByEstado(EstadoAsistencia estado) {
        return asistenciaRepository.findByEstado(estado);
    }

    public List<Asistencia> findByClaseIdAndEstado(Long claseId, EstadoAsistencia estado) {
        return asistenciaRepository.findByClaseIdAndEstado(claseId, estado);
    }

    @Transactional
    public void registrarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Transactional(readOnly = true)
    public void consultarAsistencia() {
        throw new UnsupportedOperationException("Método no implementado");
    }

    // ✅ Reporte por estudiante y clase específica
    public List<ReporteAsistenciaDTO> generarReportePorEstudianteYClase(Long estudianteId, Long claseId) {
        List<Asistencia> asistencias = this.findByEstudianteIdAndClaseId(estudianteId, claseId);

        return asistencias.stream().map(asistencia -> {
            return new ReporteAsistenciaDTO(
                    asistencia.getEstudiante().getPersona().getNombre(),
                    asistencia.getEstudiante().getCorreoEstudiantil(),
                    asistencia.getClase().getNombre(),
                    asistencia.getClase().getFecha(),
                    asistencia.getClase().getTemaVisto(),
                    asistencia.getClase().getDocente().getPersona().getNombre(),
                    asistencia.getEstado()
            );
        }).collect(Collectors.toList());
    }

    // ✅ NUEVO: Reporte general por estudiante (todas las clases)
    public List<ReporteAsistenciaDTO> generarReportePorEstudiante(Long estudianteId) {
        List<Asistencia> asistencias = this.findByEstudianteId(estudianteId);

        return asistencias.stream().map(asistencia -> {
            return new ReporteAsistenciaDTO(
                    asistencia.getEstudiante().getPersona().getNombre(),
                    asistencia.getEstudiante().getCorreoEstudiantil(),
                    asistencia.getClase().getNombre(),
                    asistencia.getClase().getFecha(),
                    asistencia.getClase().getTemaVisto(),
                    asistencia.getClase().getDocente().getPersona().getNombre(),
                    asistencia.getEstado()
            );
        }).collect(Collectors.toList());
    }

    // ✅ Generador PDF reutilizable
    public byte[] generarPdfReporte(List<ReporteAsistenciaDTO> reporte) {
        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Reporte de Asistencia", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph("Estudiante: " + reporte.get(0).getNombreEstudiante()));
            document.add(new Paragraph("Correo: " + reporte.get(0).getCorreoEstudiantil()));
            document.add(new Paragraph(" ")); // espacio

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            table.addCell("Clase");
            table.addCell("Fecha");
            table.addCell("Tema Visto");
            table.addCell("Estado");

            for (ReporteAsistenciaDTO dto : reporte) {
                table.addCell(dto.getNombreClase());
                table.addCell(dto.getFechaClase().toString());
                table.addCell(dto.getTemaVisto() != null ? dto.getTemaVisto() : "N/A");
                table.addCell(dto.getEstadoAsistencia().name());
            }

            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }
    }
}
