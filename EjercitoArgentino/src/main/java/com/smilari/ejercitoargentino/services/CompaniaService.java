package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.Cuartel;
import com.smilari.ejercitoargentino.repositories.CompaniaRepository;
import com.smilari.ejercitoargentino.entities.Compania;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompaniaService {

    private final CompaniaRepository repository;
    private final CuartelService cuartelService;

    public List<Compania> getAll() {
        return repository.findAll();
    }

    public Compania getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<Compania> getByActividad(String actividad) {
        return repository.findByActividad(actividad);
    }

    public void save(Compania compania, List<Long> cuartelesIds) {
        if (cuartelesIds != null && !cuartelesIds.isEmpty()) {
            List<Cuartel> cuarteles = cuartelService.getAllByIds(cuartelesIds);
            compania.setCuarteles(cuarteles);

            for (Cuartel cuartel : compania.getCuarteles()) {
                if (!cuartel.getCompanias().contains(compania)) {
                    cuartel.getCompanias().add(compania);
                    cuartelService.save(cuartel);
                }
            }
        }

        repository.save(compania);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }
}