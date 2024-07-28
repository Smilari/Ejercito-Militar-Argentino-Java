package com.smilari.ejercitoargentino.config;

import com.smilari.ejercitoargentino.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Permitir acceso público a los recursos estáticos
                                .requestMatchers("/css/**", "/static/js/**", "/images/**").permitAll()
                                // Configurar los endpoints públicos
                                .requestMatchers("/login", "/register", "/logout").permitAll()


                                // Configurar los endpoints privados
                                .requestMatchers("/profile").hasAnyRole("OFICIAL", "SUBOFICIAL", "SOLDADO")
                                .requestMatchers("/home").hasAnyRole("OFICIAL", "SUBOFICIAL", "SOLDADO")
                                .requestMatchers("/create/**").hasRole("OFICIAL")

                                // Configurar el resto de los endpoints (no especificados)
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de inicio de sesión
                        .defaultSuccessUrl("/home", true));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // Solo para pruebas
        return new BCryptPasswordEncoder(); // Para producción
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
