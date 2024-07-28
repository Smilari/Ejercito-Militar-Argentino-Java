package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private UserService userService;

    @GetMapping({"/", ""})
    public String redirectHome() {
        return "redirect:/home";
    }

    @GetMapping({"/home"})
    public String defaultAfterLogin() {
        // Verifica si el usuario fue autenticado anteriormente y redirige a la página correspondiente
        if (!userService.isSomeoneAuthenticated()) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/profile")
    public String mostrarPerfilUsuario(Model model) {
        model.addAttribute("user", userService.getLoggedUser());
        return "profile";
    }
}
