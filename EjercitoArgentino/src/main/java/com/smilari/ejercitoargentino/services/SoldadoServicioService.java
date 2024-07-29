package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.SoldadoServicio;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.entities.Servicio;
import com.smilari.ejercitoargentino.repositories.SoldadoServicioRepository;
import com.smilari.ejercitoargentino.repositories.ServicioRepository;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SoldadoServicioService {

    private final SoldadoServicioRepository soldadoServicioRepository;
    private final UserRepository userRepository;
    private final ServicioRepository servicioRepository;

    public void assignServices(Long userId, List<Long> serviceIds) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        List<Servicio> services = servicioRepository.findAllById(serviceIds);
        List<SoldadoServicio> soldadoServicios = services.stream()
                .map(service -> new SoldadoServicio(null, user, service, null))
                .collect(Collectors.toList());
        soldadoServicioRepository.saveAll(soldadoServicios);
    }

    public void saveService(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    public Servicio getServiceById(Long id) {
        return servicioRepository.findById(id).orElseThrow(() -> new RuntimeException("Servicio not found"));
    }

    public List<Servicio> getAllServices() {
        return servicioRepository.findAll();
    }

    public void deleteById(Long soldadoServicioId) {
        soldadoServicioRepository.deleteById(soldadoServicioId);
    }

    public SoldadoServicio getById(Long soldadoServicioId) {
        return soldadoServicioRepository.findById(soldadoServicioId).orElseThrow(() -> new RuntimeException("SoldadoServicio not found"));
    }

    public void save(SoldadoServicio soldadoServicio) {
        soldadoServicioRepository.save(soldadoServicio);
    }

    public List<SoldadoServicio> findByUser(UserEntity user) {
        return soldadoServicioRepository.findByUser(user);
    }
}