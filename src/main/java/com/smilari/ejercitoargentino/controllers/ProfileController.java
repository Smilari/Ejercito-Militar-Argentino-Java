package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.entities.Role;
import com.smilari.ejercitoargentino.entities.SoldadoServicio;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.services.SoldadoServicioService;
import com.smilari.ejercitoargentino.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;
    private SoldadoServicioService soldadoServicioService;

    @GetMapping
    public String mostrarPerfilUsuario(Model model) {
        UserEntity user = userService.getById(userService.getLoggedUser().getId());
        model.addAttribute("user", user);
        if (user.getRole() == Role.SOLDADO) {
            List<SoldadoServicio> serviciosAsignados = soldadoServicioService.findByUser(user);
            model.addAttribute("serviciosAsignados", serviciosAsignados);
        }

        return "profile";
    }

    @PostMapping("/completarServicio")
    public String completarServicio(@RequestParam Long soldadoServicioId) {
        SoldadoServicio soldadoServicio = soldadoServicioService.getById(soldadoServicioId);
        soldadoServicio.setFechaRealizacion(LocalDate.now());
        soldadoServicioService.save(soldadoServicio);
        return "redirect:/profile";
    }
}
