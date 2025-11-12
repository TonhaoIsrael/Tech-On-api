package com.israelgestaoos.apitechone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()  // libera todas as rotas REST
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login.disable()) // remove tela de login
                .httpBasic(basic -> basic.disable()); // desativa autenticação básica
        return http.build();
    }
}
