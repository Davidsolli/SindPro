package com.pidw.sindPro.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/notifications").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/notifications/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/notifications/all").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/notifications/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/notifications/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/me").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/visitors/{userId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/visitors/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/visitors/all/{userId}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/visitors/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/visitors/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/warnings").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/warnings/all").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/warnings/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/warnings/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/password/request-reset").permitAll()
                        .requestMatchers(HttpMethod.POST, "/password/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/common-spaces").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/common-spaces/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/common-spaces").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/common-spaces/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/common-spaces/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/reservation").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reservation/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reservation").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/reservation/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/reservation/{id}").authenticated()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}