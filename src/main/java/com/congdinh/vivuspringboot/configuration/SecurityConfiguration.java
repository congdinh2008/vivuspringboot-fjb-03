package com.congdinh.vivuspringboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Allow anonymous access to /auth/** and /css/**, /js/**, /images/**
                        .requestMatchers("/auth/**").permitAll()
                        // Allow anonymous access to / => home
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/contact/**").permitAll()
                        // Allow anonymous access to static files: /css/**, /js/**, /images/**
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // Allow only Admin access to /categories/**
                        .requestMatchers("/categories/**").hasRole("ADMIN")
                        // Allow both of Admin and User with authenticated access to /categories/**
                        .requestMatchers("/products/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/login?error"))
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/auth/access-denied"));
        return http.build();
    }
}
