package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.Role;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return repository.getReferenceById(id);
    }

    public Optional<UserEntity> getUserByName(String username) {
        return repository.findByUsername(username);
    }

    public void saveUser(UserEntity userEntity) {
        repository.save(userEntity);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public long countUsers() {
        return repository.count();
    }

    public Boolean isAnyUserRegistered() {
        return countUsers() > 0;
    }

    public void registerFirstUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.OFICIAL); // El primer usuario registrado será un oficial
        repository.save(user);
    }
}