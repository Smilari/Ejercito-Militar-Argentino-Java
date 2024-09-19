package com.smilari.ejercitoargentino.repositories;

import com.smilari.ejercitoargentino.entities.CuerpoEjercito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuerpoEjercitoRepository extends JpaRepository<CuerpoEjercito, Long> {
    Optional<CuerpoEjercito> findByDenominacion(String denominacion);
}
