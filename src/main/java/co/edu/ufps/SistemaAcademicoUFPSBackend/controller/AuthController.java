package co.edu.ufps.SistemaAcademicoUFPSBackend.controller;

import co.edu.ufps.SistemaAcademicoUFPSBackend.security.JwtUtil;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.PasswordResetService;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.LoginRequest;
import co.edu.ufps.SistemaAcademicoUFPSBackend.DTO.LoginResponse;
import co.edu.ufps.SistemaAcademicoUFPSBackend.model.Persona;
import co.edu.ufps.SistemaAcademicoUFPSBackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PersonaRepository personaRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String correo = loginRequest.getCorreo();
        String contrasena = loginRequest.getContrasena();

        Persona persona = personaRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contrasena, persona.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Obtener UserDetails de Persona
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                persona.getCorreo(),
                persona.getContrasena(),
                List.of(new SimpleGrantedAuthority("ROLE_" + persona.getRol().getNombre())));

        // Generar el token con UserDetails
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
            @RequestParam("nuevaContrasena") String nuevaContrasena) {
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado");
        }

        String correo = jwtUtil.getUsernameFromToken(token);

        Persona persona = personaRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Codificar la nueva contraseña
        persona.setContrasena(passwordEncoder.encode(nuevaContrasena));
        personaRepo.save(persona);

        return ResponseEntity.ok("Contraseña restablecida con éxito");
    }

    @PostMapping("/verify-identity")
    public ResponseEntity<String> verifyIdentity(@RequestParam("correo") String correo,
            @RequestParam("codigo") String codigo) {
        // Validar el código recibido (lo deberías comparar con el que se guardó en el
        // backend o en la base de datos)
        if (isValidCode(correo, codigo)) {
            return ResponseEntity.ok("Identidad verificada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código de verificación incorrecto");
        }
    }

    private boolean isValidCode(String correo, String codigo) {
        // Lógica para validar el código (por ejemplo, compararlo con el guardado en la
        // base de datos)
        return true; // Esto es solo un ejemplo, debes implementarlo de acuerdo a tu sistema
    }

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestParam String correo) {
        passwordResetService.sendResetPasswordEmail(correo);
        return ResponseEntity.ok("Correo enviado (o link generado)");
    }

}
