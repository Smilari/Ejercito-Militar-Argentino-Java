package com.smilari.ejercitoargentino.repositories;

import com.smilari.ejercitoargentino.entities.Servicio;
import com.smilari.ejercitoargentino.entities.SoldadoServicio;
import com.smilari.ejercitoargentino.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldadoServicioRepository extends JpaRepository<SoldadoServicio, Long> {
    SoldadoServicio findByUserAndServicio(UserEntity user, Servicio servicio);
    List<SoldadoServicio> findByUser(UserEntity user);
}