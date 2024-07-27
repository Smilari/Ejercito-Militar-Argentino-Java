package com.smilari.ejercitoargentino.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oficial")
public class OficialController {

    @GetMapping("/home")
    public String home() {
        return "oficial/home";
    }
}