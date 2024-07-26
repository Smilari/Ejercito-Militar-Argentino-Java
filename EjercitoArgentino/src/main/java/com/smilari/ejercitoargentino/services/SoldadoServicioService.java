package com.smilari.ejercitoargentino.services;

import com.smilari.ejercitoargentino.entities.SoldadoServicio;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.entities.Servicio;
import com.smilari.ejercitoargentino.repositories.SoldadoServicioRepository;
import com.smilari.ejercitoargentino.repositories.ServicioRepository;
import com.smilari.ejercitoargentino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SoldadoServicioService {

    private final SoldadoServicioRepository soldadoServicioRepository;
    private final UserRepository userEntityRepository;
    private final ServicioRepository servicioRepository;

    @Autowired
    public SoldadoServicioService (SoldadoServicioRepository soldadoServicioRepository, UserRepository userEntityRepository, ServicioRepository servicioRepository) {
        this.soldadoServicioRepository = soldadoServicioRepository;
        this.userEntityRepository = userEntityRepository;
        this.servicioRepository = servicioRepository;
    }

    public SoldadoServicio asignarServicio(Long userId, Long servicioId, LocalDate fechaRealizacion) {
        UserEntity user = userEntityRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Servicio servicio = servicioRepository.findById(servicioId).orElseThrow(() -> new RuntimeException("Servicio not found"));

        SoldadoServicio soldadoServicio = new SoldadoServicio();
        soldadoServicio.setUser(user);
        soldadoServicio.setServicio(servicio);
        soldadoServicio.setFechaRealizacion(fechaRealizacion);

        return soldadoServicioRepository.save(soldadoServicio);
    }
}