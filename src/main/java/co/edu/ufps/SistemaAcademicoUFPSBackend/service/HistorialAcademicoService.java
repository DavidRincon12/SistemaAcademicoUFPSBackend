package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.DetalleAsignaturaDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.InformeAcademicoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.EstadoCurso;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.HistorialAcademico;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Estudiante;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.HistorialAcademicoRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialAcademicoService {

    private final HistorialAcademicoRepository historialAcademicoRepository;

    public HistorialAcademicoService(HistorialAcademicoRepository historialAcademicoRepository) {
        this.historialAcademicoRepository = historialAcademicoRepository;
    }

    public List<HistorialAcademico> findAll() {
        return historialAcademicoRepository.findAll();
    }

    public HistorialAcademico findById(Long id) {
        return historialAcademicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado con ID: " + id));
    }

    public HistorialAcademico save(HistorialAcademico historial) {
        return historialAcademicoRepository.save(historial);
    }

    public void delete(Long id) {
        historialAcademicoRepository.deleteById(id);
    }

    public List<HistorialAcademico> findByEstudianteId(Long estudianteId) {
        return historialAcademicoRepository.findByEstudianteId(estudianteId);
    }

    public InformeAcademicoDTO generarInformePorEstudiante(Long estudianteId) {
        List<HistorialAcademico> historiales = historialAcademicoRepository.findByEstudianteId(estudianteId);

        if (historiales.isEmpty()) {
            throw new RuntimeException("No se encontraron datos académicos para este estudiante.");
        }

        Estudiante estudiante = historiales.get(0).getEstudiante();

        InformeAcademicoDTO informe = new InformeAcademicoDTO();
        informe.setEstudianteId(estudianteId);
        informe.setNombreEstudiante(estudiante.getPersona().getNombre());

        float sumaNotas = 0f;
        int cantidadAprobadas = 0, cantidadReprobadas = 0, cantidadEnCurso = 0;
        List<DetalleAsignaturaDTO> detalles = new ArrayList<>();

        for (HistorialAcademico h : historiales) {
            DetalleAsignaturaDTO detalle = new DetalleAsignaturaDTO();
            detalle.setAsignaturaNombre(h.getAsignatura().getMateria().getNombre());
            detalle.setNota(h.getNota());
            detalle.setEstado(h.getEstado());
            detalle.setPeriodo(h.getPeriodo());
            detalles.add(detalle);

            switch (h.getEstado()) {
                case APROBADO -> {
                    sumaNotas += h.getNota();
                    cantidadAprobadas++;
                }
                case REPROBADO -> cantidadReprobadas++;
                case EN_CURSO -> cantidadEnCurso++;
            }
        }

        informe.setPromedioPonderado(cantidadAprobadas > 0 ? sumaNotas / cantidadAprobadas : 0f);
        informe.setTotalAprobadas(cantidadAprobadas);
        informe.setTotalReprobadas(cantidadReprobadas);
        informe.setTotalEnCurso(cantidadEnCurso);
        informe.setDetalle(detalles);

        return informe;
    }

    public byte[] generarInformePDF(Long estudianteId) {
        InformeAcademicoDTO informe = this.generarInformePorEstudiante(estudianteId);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Informe de Rendimiento Académico", titleFont));
            document.add(new Paragraph("Estudiante: " + informe.getNombreEstudiante(), bodyFont));
            document.add(new Paragraph("Promedio Ponderado: " + informe.getPromedioPonderado(), bodyFont));
            document.add(new Paragraph("Asignaturas Aprobadas: " + informe.getTotalAprobadas(), bodyFont));
            document.add(new Paragraph("Asignaturas Reprobadas: " + informe.getTotalReprobadas(), bodyFont));
            document.add(new Paragraph("Asignaturas en Curso: " + informe.getTotalEnCurso(), bodyFont));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4, 2, 2, 2});

            table.addCell("Asignatura");
            table.addCell("Nota");
            table.addCell("Estado");
            table.addCell("Periodo");

            for (DetalleAsignaturaDTO detalle : informe.getDetalle()) {
                table.addCell(detalle.getAsignaturaNombre());
                table.addCell(detalle.getNota().toString());
                table.addCell(detalle.getEstado().toString());
                table.addCell(detalle.getPeriodo());
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF: " + e.getMessage());
        }
    }
}
