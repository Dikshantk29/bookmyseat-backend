package com.dikshant.bookmyseat.config;

import com.dikshant.bookmyseat.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()

                        // User management is restricted to admins.
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // Admin-only catalog/configuration write operations.
                        .requestMatchers("POST", "/api/cities/**").hasRole("ADMIN")
                        .requestMatchers("POST", "/api/movies/**").hasRole("ADMIN")
                        .requestMatchers("PUT", "/api/movies/**").hasRole("ADMIN")
                        .requestMatchers("DELETE", "/api/movies/**").hasRole("ADMIN")
                        .requestMatchers("POST", "/api/theaters/**").hasRole("ADMIN")
                        .requestMatchers("POST", "/api/screens/**").hasRole("ADMIN")
                        .requestMatchers("POST", "/api/seats/**").hasRole("ADMIN")
                        .requestMatchers("DELETE", "/api/seats/**").hasRole("ADMIN")
                        .requestMatchers("POST", "/api/shows/**").hasRole("ADMIN")

                        // All other endpoints require a valid JWT.
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
