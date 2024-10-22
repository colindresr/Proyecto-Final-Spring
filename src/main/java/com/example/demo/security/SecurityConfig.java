package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Deshabilitar todas las configuraciones de seguridad
        http
                .csrf(csrf -> csrf.disable())  // Desactivar CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Permitir todas las solicitudes
                )
                .formLogin(login -> login.disable())  // Desactivar el formulario de inicio de sesión
                .httpBasic(basic -> basic.disable())  // Desactivar autenticación básica
                .logout(logout -> logout.disable());  // Desactivar el logout

        return http.build();
    }
}