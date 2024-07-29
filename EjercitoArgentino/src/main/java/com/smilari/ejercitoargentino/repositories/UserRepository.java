package com.smilari.ejercitoargentino.repositories;

import com.smilari.ejercitoargentino.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByIdOrUsernameContainingIgnoreCase(Long id, String username);

    List<UserEntity> findByUsernameContainingIgnoreCase(String username);

}
