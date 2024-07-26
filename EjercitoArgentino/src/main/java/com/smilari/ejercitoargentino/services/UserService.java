package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

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
}