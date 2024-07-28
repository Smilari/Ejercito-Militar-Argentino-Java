package com.smilari.ejercitoargentino.repositories;

import com.smilari.ejercitoargentino.entities.Cuartel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuartelRepository extends JpaRepository<Cuartel, Long> {

    Optional<Cuartel> findByName(String name);
}
