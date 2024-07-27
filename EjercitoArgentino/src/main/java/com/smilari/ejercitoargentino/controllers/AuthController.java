package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.repositories.UserRepository;
import com.smilari.ejercitoargentino.services.UserService;
import com.smilari.ejercitoargentino.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String login(Model model) {
        if (!userService.isAnyUserRegistered()) {
            return "redirect:/register";
        }
        return "login";
    }

    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
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

    @GetMapping({"/default", "/"})
    public String defaultAfterLogin() {
        // Verifica si el usuario fue autenticado anteriormente y redirige a la página correspondiente
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        return "redirect:/" + determineTargetUrl();
    }

    private String determineTargetUrl() {
        // Esta lógica determina la redirección basada en el rol del usuario logeado
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = user.getRole().name();

        return switch (role) {
            case "OFICIAL" -> "oficial/home";
            case "SUBOFICIAL" -> "suboficial/home";
            case "SOLDADO" -> "soldado/home";
            default -> throw new IllegalStateException();
        };
    }
}
