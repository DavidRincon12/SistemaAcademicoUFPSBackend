package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteReservaEstadoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteReservaEstudianteDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteReservaUsoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.*;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteReservaService {

    @Autowired
    private ReservaRecursoRepository reservaRepo;

    @Autowired
    private PrestamoMaterialRepository prestamoRepo;

    @Autowired
    private RecursoAcademicoRepository recursoRepo;

    public List<ReporteReservaEstudianteDTO> getReservasPorEstudiante(Long personaId) {
        List<ReservaRecurso> reservas = reservaRepo.findAll().stream()
                .filter(r -> r.getRecursoAcademico().getResponsable() != null
                        && r.getRecursoAcademico().getResponsable().getId().equals(personaId))
                .collect(Collectors.toList());

        return reservas.stream().map(r -> new ReporteReservaEstudianteDTO(
                r.getRecursoAcademico().getResponsable().getNombre(),
                r.getId(),
                r.getRecursoAcademico().getNombre(),
                r.getFechaInicio(),
                r.getFechaFin(),
                r.getEstado()
        )).collect(Collectors.toList());
    }

    public ReporteReservaUsoDTO getUsoPorRecurso(Long recursoId) {
        RecursoAcademico recurso = recursoRepo.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        int totalReservas = (int) reservaRepo.findAll().stream()
                .filter(r -> r.getRecursoAcademico().getId().equals(recursoId)).count();
        int totalPrestamos = (int) prestamoRepo.findByRecurso_Id(recursoId).size();

        return new ReporteReservaUsoDTO(
                recurso.getNombre(),
                recurso.getTipoRecurso(),
                totalReservas,
                totalPrestamos
        );
    }

    public List<ReporteReservaEstadoDTO> getEstadoActualRecursos() {
        return recursoRepo.findAll().stream()
                .map(r -> new ReporteReservaEstadoDTO(
                        r.getNombre(),
                        r.getTipoRecurso(),
                        r.isEnMantenimiento() ? "En Mantenimiento" : (r.isDisponible() ? "Disponible" : "No disponible")
                )).collect(Collectors.toList());
    }

    public byte[] exportarEstadoRecursosPDF() throws DocumentException {
        List<ReporteReservaEstadoDTO> estados = getEstadoActualRecursos();
        Document documento = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(documento, baos);
        documento.open();

        documento.add(new Paragraph("Reporte de Estado Actual de Recursos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidthPercentage(100);
        tabla.addCell("Nombre Recurso");
        tabla.addCell("Tipo Recurso");
        tabla.addCell("Estado");

        for (ReporteReservaEstadoDTO estado : estados) {
            tabla.addCell(estado.getNombreRecurso());
            tabla.addCell(estado.getTipoRecurso());
            tabla.addCell(estado.getEstado());
        }

        documento.add(tabla);
        documento.close();
        return baos.toByteArray();
    }

    public byte[] exportarReservasEstudiantePDF(Long personaId) throws DocumentException {
        List<ReporteReservaEstudianteDTO> reservas = getReservasPorEstudiante(personaId);
        Document documento = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(documento, baos);
        documento.open();

        documento.add(new Paragraph("Reporte de Reservas por Estudiante", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.addCell("Recurso");
        tabla.addCell("Fecha Inicio");
        tabla.addCell("Fecha Fin");
        tabla.addCell("Estado");
        tabla.addCell("ID Reserva");

        for (ReporteReservaEstudianteDTO r : reservas) {
            tabla.addCell(r.getRecurso());
            tabla.addCell(r.getFechaInicio().toString());
            tabla.addCell(r.getFechaFin().toString());
            tabla.addCell(r.getEstado());
            tabla.addCell(String.valueOf(r.getIdReserva()));
        }

        documento.add(tabla);
        documento.close();
        return baos.toByteArray();
    }

    public byte[] exportarUsoRecursoPDF(Long recursoId) throws DocumentException {
        ReporteReservaUsoDTO uso = getUsoPorRecurso(recursoId);
        Document documento = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(documento, baos);
        documento.open();

        documento.add(new Paragraph("Reporte de Uso del Recurso", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.addCell("Nombre Recurso");
        tabla.addCell("Tipo");
        tabla.addCell("Total Reservas");
        tabla.addCell("Total Préstamos");

        tabla.addCell(uso.getNombreRecurso());
        tabla.addCell(uso.getTipoRecurso());
        tabla.addCell(String.valueOf(uso.getTotalReservas()));
        tabla.addCell(String.valueOf(uso.getTotalPrestamos()));

        documento.add(tabla);
        documento.close();
        return baos.toByteArray();
    }
}