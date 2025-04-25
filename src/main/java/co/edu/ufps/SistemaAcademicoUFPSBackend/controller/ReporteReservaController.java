package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteReservaEstadoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteReservaEstudianteDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteReservaUsoDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.ReporteReservaService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reportereserva")
@CrossOrigin(origins = "*")
public class ReporteReservaController {

    @Autowired
    private ReporteReservaService reporteService;

    @GetMapping("/estudiante/{personaId}")
    public List<ReporteReservaEstudianteDTO> getReservasPorEstudiante(@PathVariable Long personaId) {
        return reporteService.getReservasPorEstudiante(personaId);
    }

    @GetMapping("/uso-recurso/{recursoId}")
    public ReporteReservaUsoDTO getUsoPorRecurso(@PathVariable Long recursoId) {
        return reporteService.getUsoPorRecurso(recursoId);
    }

    @GetMapping("/estado-recursos")
    public List<ReporteReservaEstadoDTO> getEstadoRecursos() {
        return reporteService.getEstadoActualRecursos();
    }

    @GetMapping("/estado-recursos/pdf")
    public ResponseEntity<byte[]> descargarEstadoRecursosPDF() throws DocumentException {
        byte[] pdfBytes = reporteService.exportarEstadoRecursosPDF();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("estado_recursos.pdf").build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/estudiante/{personaId}/pdf")
    public ResponseEntity<byte[]> descargarReservasEstudiantePDF(@PathVariable Long personaId) throws DocumentException {
        byte[] pdfBytes = reporteService.exportarReservasEstudiantePDF(personaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("reservas_estudiante.pdf").build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/uso-recurso/{recursoId}/pdf")
    public ResponseEntity<byte[]> descargarUsoRecursoPDF(@PathVariable Long recursoId) throws DocumentException {
        byte[] pdfBytes = reporteService.exportarUsoRecursoPDF(recursoId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("uso_recurso.pdf").build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
