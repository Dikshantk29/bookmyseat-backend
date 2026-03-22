package com.dikshant.bookmyseat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF since this is a REST API (typically used with JWT or stateless auth)
                .csrf(csrf -> csrf.disable())

                // Set up endpoint authorization
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests to /public/** and your auth endpoints
                        .requestMatchers("/public/**", "/api/users/register", "/api/users/login").permitAll()

                        // Require authentication for /user/** and booking endpoints
                        .requestMatchers("/user/**", "/api/bookings/**").authenticated()

                        // Fallback for everything else
                        .anyRequest().authenticated())
                // Use basic authentication for simplicity (you can later upgrade to JWT)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}