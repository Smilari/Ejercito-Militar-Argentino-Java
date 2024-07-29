package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.entities.Servicio;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.entities.SoldadoServicio;
import com.smilari.ejercitoargentino.services.SoldadoServicioService;
import com.smilari.ejercitoargentino.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/services")
@AllArgsConstructor
public class ServiceController {

    private SoldadoServicioService soldadoServicioService;
    private UserService userService;

    @GetMapping
    public String getServiceForm(Model model) {
        model.addAttribute("service", new Servicio());
        return "services";
    }

    @PostMapping
    public String saveService(Servicio servicio) {
        soldadoServicioService.saveService(servicio);
        return "redirect:/home";
    }

    @GetMapping("/assign/{id}")
    public String assignServicesForm(@PathVariable Long id, Model model) {
        UserEntity user = userService.getById(id);
        if (!user.getRole().name().equals("SOLDADO")) {
            return "error";
        }

        List<Servicio> allServicios = soldadoServicioService.getAllServices();
        List<Servicio> assignedServicios = user.getSoldadoServicios().stream()
                .map(SoldadoServicio::getServicio)
                .toList();
        List<Servicio> availableServicios = allServicios.stream()
                .filter(s -> !assignedServicios.contains(s))
                .collect(Collectors.toList());


        model.addAttribute("user", user);
        model.addAttribute("services", availableServicios);
        return "assign/service";
    }

    @PostMapping("/assign/{id}")
    public String assignServices(@PathVariable Long id, @RequestParam List<Long> serviceIds) {
        soldadoServicioService.assignServices(id, serviceIds);
        return "redirect:/home";
    }

    @PostMapping("/unassign")
    public String unassignService(@RequestParam Long soldadoServicioId) {
        SoldadoServicio soldadoServicio = soldadoServicioService.getById(soldadoServicioId);
        Long userId = soldadoServicio.getUser().getId();
        soldadoServicioService.deleteById(soldadoServicioId);
        return "redirect:/services/assign/" + userId; // Redirigir al formulario de asignación
    }
}
