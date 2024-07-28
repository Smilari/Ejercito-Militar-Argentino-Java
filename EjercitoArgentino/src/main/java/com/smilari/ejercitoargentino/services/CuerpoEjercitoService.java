package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.CuerpoEjercito;

import com.smilari.ejercitoargentino.repositories.CuerpoEjercitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CuerpoEjercitoService {

    private final CuerpoEjercitoRepository repository;

    public List<CuerpoEjercito> getAll() {
        return repository.findAll();
    }

    public CuerpoEjercito getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<CuerpoEjercito> getByDenominacion(String denominacion) {
        return repository.findByDenominacion(denominacion);
    }

    public void save(CuerpoEjercito cuerpo) {
        repository.save(cuerpo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }
}