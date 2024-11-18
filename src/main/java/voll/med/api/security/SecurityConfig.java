package voll.med.api.security;

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
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/medicos").authenticated()
                        .requestMatchers(HttpMethod.GET, "/medicos/{slug}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/medicos").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/medicos/editar/{slug}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/medicos/deletar/{slug}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes").authenticated()
                        .requestMatchers(HttpMethod.GET, "/pacientes/{slug}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/pacientes").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/pacientes/editar/{slug}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/deletar/{slug}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/auth/logout")
                        .permitAll()
                )
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("*"));
                    return corsConfiguration;
                }))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
