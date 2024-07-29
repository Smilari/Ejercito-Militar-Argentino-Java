package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.Cuartel;
import com.smilari.ejercitoargentino.repositories.CuartelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CuartelService {

    private final CuartelRepository repository;

    public List<Cuartel> getAll() {
        return repository.findAll();
    }

    public Cuartel getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<Cuartel> getByName(String name) {
        return repository.findByName(name);
    }

    public void save(Cuartel cuartel) {
        repository.save(cuartel);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    public List<Cuartel> getAllByIds(List<Long> cuartelesIds) {
        return repository.findAllById(cuartelesIds);
    }
}