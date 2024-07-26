package com.smilari.ejercitoargentino.config;

import com.smilari.ejercitoargentino.entities.Role;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.count() == 0) {
            UserEntity defaultUser = UserEntity.builder()
                    .username("admin")
                    .password("admin")
                    .role(Role.OFICIAL)
                    .build();
            userRepository.save(defaultUser);
        }
    }
}