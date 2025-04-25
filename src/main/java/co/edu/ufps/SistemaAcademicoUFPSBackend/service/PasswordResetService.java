package co.edu.ufps.SistemaAcademicoUFPSBackend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import co.edu.ufps.SistemaAcademicoUFPSBackend.security.JwtUtil;

@Service
public class PasswordResetService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    @Autowired
private EmailService emailService;

    public void sendResetPasswordEmail(String correo) {
        Persona persona = personaRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        String resetToken = jwtUtil.generateToken(correo);
        String resetLink = "http://localhost:8080/auth/reset-password?token=" + resetToken;

    
        String mensaje = "Hola " + persona.getNombre() + ",\n\n"
                       + "Haga clic en el siguiente enlace para restablecer su contraseña:\n" + resetLink;
    
        try {
            emailService.sendEmail(correo, "Restablecer contraseña", mensaje);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }
}
