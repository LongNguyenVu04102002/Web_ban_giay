package com.example.datn.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SecurityUtils securityUtils() {
        return new SecurityUtils();
    }
}