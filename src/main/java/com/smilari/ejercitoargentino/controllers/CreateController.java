package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.entities.Compania;
import com.smilari.ejercitoargentino.entities.Cuartel;
import com.smilari.ejercitoargentino.entities.CuerpoEjercito;
import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.services.CompaniaService;
import com.smilari.ejercitoargentino.services.CuartelService;
import com.smilari.ejercitoargentino.services.CuerpoEjercitoService;
import com.smilari.ejercitoargentino.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/create")
@AllArgsConstructor
public class CreateController {

    private final UserService userService;
    private final CuartelService cuartelService;
    private final CompaniaService companiaService;
    private final CuerpoEjercitoService cuerpoEjercitoService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping({"/", ""})
    public String homeCreate() {
        return "create";
    }

    @GetMapping("/user")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("cuerpos", cuerpoEjercitoService.getAll());
        model.addAttribute("companias", companiaService.getAll());
        model.addAttribute("cuarteles", cuartelService.getAll());
        return "create/user";
    }

    @PostMapping("/user")
    public String createUser(@RequestParam String password, @ModelAttribute UserEntity user) {
        if (user.getCuerpoEjercito().getId() == null) {
            user.setCuerpoEjercito(null);
        }
        if (user.getCompania().getId() == null) {
            user.setCompania(null);
        }
//        if(user.getCuartel().getId() == null) {
//            user.setCuartel(null);
//        }
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return "redirect:/create";
    }

    @GetMapping("/cuartel")
    public String createCuartelForm(Model model) {
        model.addAttribute("cuartel", new Cuartel());
        return "create/cuartel";
    }

    @PostMapping("/cuartel")
    public String createCuartel(@ModelAttribute Cuartel cuartel) {
        cuartelService.save(cuartel);
        return "redirect:/create";
    }

    @GetMapping("/compania")
    public String createCompaniaForm(Model model) {
        model.addAttribute("compania", new Compania());
        model.addAttribute("cuarteles", cuartelService.getAll());
        return "create/compania";
    }

    @PostMapping("/compania")
    public String createCompania(@ModelAttribute Compania compania, @RequestParam(required = false) List<Long> cuartelesIds) {
        companiaService.save(compania, cuartelesIds);
        return "redirect:/create";
    }

    @GetMapping("/cuerpo")
    public String createCuerpoForm(Model model) {
        model.addAttribute("cuerpoEjercito", new CuerpoEjercito());
        return "create/cuerpo";
    }

    @PostMapping("/cuerpo")
    public String createCuerpo(@ModelAttribute CuerpoEjercito cuerpoEjercito) {
        cuerpoEjercitoService.save(cuerpoEjercito);
        return "redirect:/create";
    }
}
