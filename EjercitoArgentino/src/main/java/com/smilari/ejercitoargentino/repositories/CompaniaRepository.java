package com.smilari.ejercitoargentino.repositories;

import com.smilari.ejercitoargentino.entities.Compania;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompaniaRepository extends JpaRepository<Compania, Long> {
    Optional<Compania> findByActividad(String actividad);
}
