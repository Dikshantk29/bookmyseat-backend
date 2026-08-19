package com.dikshant.bookmyseat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// Marks this class as a configuration class (Spring will read it at startup)
public class CorsConfig {

    @Bean
    // Registers this method as a Spring Bean so it gets applied globally
    public WebMvcConfigurer corsConfigurer() {

        // WebMvcConfigurer allows us to customize Spring MVC settings
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        // Apply CORS rules to ALL endpoints (/** = every API)

                        .allowedOrigins("*")
                        // Allow requests from ANY frontend (React, Angular, etc.)
                        // Without this → browser blocks requests from different origin

                        .allowedMethods("GET", "POST", "PUT", "DELETE");
                // Allow these HTTP methods from frontend
            }
        };
    }
}