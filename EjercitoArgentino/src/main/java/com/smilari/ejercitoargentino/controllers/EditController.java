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

@Controller
@RequestMapping("/edit")
@AllArgsConstructor
public class EditController {

    private final UserService userService;
    private final CuartelService cuartelService;
    private final CompaniaService companiaService;
    private final CuerpoEjercitoService cuerpoEjercitoService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping({"/", ""})
    public String homeEdit() {
        return "edit";
    }

    @GetMapping("/edit/user/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "edit/user";
    }

    @PostMapping("/edit/user")
    public String editUser(@RequestParam String password, @ModelAttribute UserEntity user) {
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return "redirect:/edit";
    }

    @GetMapping("/edit/cuartel/{id}")
    public String editCuartelForm(@PathVariable Long id, Model model) {
        model.addAttribute("cuartel", cuartelService.getById(id));
        return "edit/cuartel";
    }

    @PostMapping("/edit/cuartel")
    public String editCuartel(@ModelAttribute Cuartel cuartel) {
        cuartelService.save(cuartel);
        return "redirect:/edit";
    }

    @GetMapping("/edit/compania/{id}")
    public String editCompaniaForm(@PathVariable Long id, Model model) {
        model.addAttribute("compania", companiaService.getById(id));
        return "edit/compania";
    }

    @PostMapping("/edit/compania")
    public String editCompania(@ModelAttribute Compania compania) {
        companiaService.save(compania);
        return "redirect:/edit";
    }

    @GetMapping("/edit/cuerpo/{id}")
    public String editCuerpoForm(@PathVariable Long id, Model model) {
        model.addAttribute("cuerpoEjercito", cuerpoEjercitoService.getById(id));
        return "edit/cuerpo";
    }

    @PostMapping("/edit/cuerpo")
    public String editCuerpo(@ModelAttribute CuerpoEjercito cuerpoEjercito) {
        cuerpoEjercitoService.save(cuerpoEjercito);
        return "redirect:/edit";
    }
}
