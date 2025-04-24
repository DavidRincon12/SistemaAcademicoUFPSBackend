package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentityVerificationService {

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public String generateVerificationCode() {
        // Generar un código aleatorio de 6 dígitos
        return String.format("%06d", random.nextInt(1000000));
    }

    public void sendVerificationEmail(String correo) {
        String code = generateVerificationCode();

        String subject = "Código de verificación";
        String body = "Tu código de verificación es: " + code;

        try {
            emailService.sendEmail(correo, subject, body);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de verificación", e);
        }

        // Aquí podrías guardar el código en la base de datos o en la sesión si es necesario
    }
}
