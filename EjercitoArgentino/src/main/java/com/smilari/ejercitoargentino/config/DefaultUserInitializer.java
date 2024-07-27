/*
package com.smilari.ejercitoargentino.config;

import com.smilari.ejercitoargentino.entities.Role;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultUserInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.count() == 0) {
            UserEntity defaultUser = UserEntity.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.OFICIAL)
                    .build();
            userRepository.save(defaultUser);
        }
    }
}
*/
