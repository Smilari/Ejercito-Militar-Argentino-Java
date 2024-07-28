package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.Role;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    public UserEntity getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<UserEntity> getByName(String username) {
        return repository.findByUsername(username);
    }

    public void save(UserEntity userEntity) {
        repository.save(userEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    public Boolean isAnyUserRegistered() {
        return count() > 0;
    }

    public void registerFirstUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.OFICIAL); // El primer usuario registrado será un oficial
        repository.save(user);
    }

    public UserEntity getLoggedUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    public String getLoggedUserRole() {
        return getLoggedUser().getRole().name();
    }

    public boolean isSomeoneAuthenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser");
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}