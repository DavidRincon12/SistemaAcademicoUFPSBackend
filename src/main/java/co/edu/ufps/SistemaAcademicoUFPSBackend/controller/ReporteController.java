package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.ReporteAsistenciaDTO;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes/asistencia")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private AsistenciaService asistenciaService;

    // ✅ Reporte de asistencias por estudiante y clase específica (ya existente)
    @GetMapping("/estudiante/{estudianteId}/clase/{claseId}")
    public ResponseEntity<List<ReporteAsistenciaDTO>> getReporteAsistenciaJSON(
            @PathVariable Long estudianteId,
            @PathVariable Long claseId
    ) {
        List<ReporteAsistenciaDTO> reporte = asistenciaService.generarReportePorEstudianteYClase(estudianteId, claseId);
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/estudiante/{estudianteId}/clase/{claseId}/pdf")
    public ResponseEntity<byte[]> getReporteAsistenciaPDF(
            @PathVariable Long estudianteId,
            @PathVariable Long claseId
    ) {
        List<ReporteAsistenciaDTO> reporte = asistenciaService.generarReportePorEstudianteYClase(estudianteId, claseId);
        byte[] pdfBytes = asistenciaService.generarPdfReporte(reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("reporte_asistencia_clase_" + claseId + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    // ✅ NUEVO: Reporte de asistencias generales por estudiante (todas las clases)
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<ReporteAsistenciaDTO>> getReportePorEstudianteJSON(@PathVariable Long estudianteId) {
        List<ReporteAsistenciaDTO> reporte = asistenciaService.generarReportePorEstudiante(estudianteId);
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/estudiante/{estudianteId}/pdf")
    public ResponseEntity<byte[]> getReportePorEstudiantePDF(@PathVariable Long estudianteId) {
        List<ReporteAsistenciaDTO> reporte = asistenciaService.generarReportePorEstudiante(estudianteId);
        byte[] pdfBytes = asistenciaService.generarPdfReporte(reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("reporte_asistencia_estudiante_" + estudianteId + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
