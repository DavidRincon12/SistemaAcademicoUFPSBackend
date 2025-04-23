package co.edu.ufps.SistemaAcademicoUFPSBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/auth/**").permitAll()
            .requestMatchers("/personas/**").permitAll()

            .requestMatchers("/estudiantes/**").permitAll()
            .requestMatchers("/matricula/**", "/inscripcion/**").hasAnyRole("ADMINISTRADOR", "ESTUDIANTE")
            .requestMatchers("/historial/**", "/asistencia/**").hasAnyRole("ADMINISTRADOR", "ESTUDIANTE")

            .requestMatchers("/docentes/**").hasRole("ADMINISTRADOR")
            .requestMatchers("/asignacion-docente/**").hasAnyRole("ADMINISTRADOR", "DOCENTE")
            .requestMatchers("/evaluaciones/**").hasRole("DOCENTE")

            .requestMatchers("/cursos/**", "/asignaturas/**").hasRole("ADMINISTRADOR")
            .requestMatchers("/inscripciones/**", "/cancelaciones/**").hasRole("ESTUDIANTE")

            .requestMatchers("/calificaciones/**").hasAnyRole("DOCENTE", "ESTUDIANTE")

            .requestMatchers("/mensajes/**", "/notificaciones/**").authenticated()

            .requestMatchers("/recursos/**").hasAnyRole("ADMINISTRADOR", "ADMINISTRATIVO")

            .requestMatchers("/usuarios/**", "/roles/**").hasRole("ADMINISTRADOR")
            .requestMatchers("/reportes/**").hasRole("ADMINISTRADOR")

            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(Customizer.withDefaults());

    return http.build();
}


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}