package co.edu.ufps.SistemaAcademicoUFPSBackend.config;

import co.edu.ufps.SistemaAcademicoUFPSBackend.security.JwtAuthenticationFilter;
import co.edu.ufps.SistemaAcademicoUFPSBackend.security.JwtUtil;
import co.edu.ufps.SistemaAcademicoUFPSBackend.service.PersonaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PersonaService personaService) {
        return personaService;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(jwtUtil, userDetailsService);
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // <-- permite todos los endpoints sin autenticación
        )
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth", "/auth/**").permitAll()
//                        .requestMatchers("/auth/request-reset", "/auth/reset-password", "/reset-password").permitAll()
//                        .requestMatchers("/personas/**", "/roles/**").hasAnyRole("ADMINISTRADOR", "ADMINISTRATIVO")
//
//                        .requestMatchers("/matricula/**", "/inscripcion/**").hasAnyRole("ADMINISTRADOR", "ESTUDIANTE")
//                        .requestMatchers("/historial/**", "/asistencia/**").hasAnyRole("ADMINISTRADOR", "ESTUDIANTE")
//
//                        .requestMatchers("/docentes/**").hasRole("ADMINISTRADOR")
//                        .requestMatchers("/asignacion-docente/**").hasAnyRole("ADMINISTRADOR", "DOCENTE")
//                        .requestMatchers("/evaluaciones/**").hasRole("DOCENTE")
//
//                        .requestMatchers("/cursos/**", "/asignaturas/**").hasRole("ADMINISTRADOR")
//                        .requestMatchers("/inscripciones/**", "/cancelaciones/**").hasRole("ESTUDIANTE")
//
//                        .requestMatchers("/calificaciones/**").hasAnyRole("DOCENTE", "ESTUDIANTE")
//
//                        .requestMatchers("/mensajes/**", "/notificaciones/**").authenticated()
//
//                        .requestMatchers("/recursos/**").hasAnyRole("ADMINISTRADOR", "ADMINISTRATIVO")
//
//                        .requestMatchers("/reportes/**").hasRole("ADMINISTRADOR")
//
//                        .anyRequest().authenticated())
//                        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
