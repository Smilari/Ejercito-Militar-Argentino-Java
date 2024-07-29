package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.repositories.UserRepository;
import com.smilari.ejercitoargentino.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        if (!userService.isAnyUserRegistered()) {
            return "redirect:/register";
        }
        return "login";
    }

    @GetMapping("/desloguear")
    public String logout() {
        userService.logout();
        return "logout";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        if (userService.isAnyUserRegistered()) {
            return "redirect:/login";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "register";
        }

        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "El nombre de usuario ya está en uso.");
            return "register";
        }

        userService.registerFirstUser(username, password);
        return "redirect:/login";
    }
}
