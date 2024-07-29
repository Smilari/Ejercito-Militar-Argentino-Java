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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                                .requestMatchers("/css/**", "/static/js/**", "/img/**").permitAll()
                                // Configurar los endpoints públicos
                                .requestMatchers("/login", "/register", "/logout").permitAll()

                                // Configurar los endpoints privados
                                .requestMatchers("/profile").hasAnyRole("OFICIAL", "SUBOFICIAL", "SOLDADO")
                                .requestMatchers("/home").hasAnyRole("OFICIAL", "SUBOFICIAL", "SOLDADO")
                                .requestMatchers("/create/**").hasRole("OFICIAL")
                                .requestMatchers("/search/**").hasAnyRole("OFICIAL", "SUBOFICIAL")
                                .requestMatchers("/edit/**").hasAnyRole("OFICIAL", "SUBOFICIAL")
                                .requestMatchers("/services/**").hasAnyRole("OFICIAL", "SUBOFICIAL")

                                // Configurar el resto de los endpoints (no especificados)
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de inicio de sesión
                        .defaultSuccessUrl("/home", true))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL de deslogueo
                        .logoutSuccessUrl("/desloguear") // URL de redirección al desloguear
                        .permitAll());
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
