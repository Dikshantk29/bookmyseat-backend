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

                .authorizeHttpRequests(auth -> auth
                        // 1. Public Auth endpoints
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()

                        // 2. Allow everyone to GET (view) movies, theaters, shows, cities, etc.
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/movies/**", "/api/theaters/**", "/api/shows/**", "/api/cities/**", "/api/screens/**", "/api/seats/**").permitAll()

                        // 3. ADMIN ONLY: Restrict all POST, PUT, DELETE requests on these endpoints
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/movies", "/api/theaters", "/api/shows", "/api/cities", "/api/screens", "/api/seats/**").hasAuthority("ROLE_ADMIN").requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/movies/**").hasAuthority("ROLE_ADMIN").requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/movies/**", "/api/seats/**").hasAuthority("ROLE_ADMIN")

                        // 4. Require authentication for user bookings
                        .requestMatchers("/api/bookings/**").authenticated()

                        // 5. Fallback
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