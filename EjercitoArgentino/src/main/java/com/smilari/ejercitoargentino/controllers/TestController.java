package com.smilari.ejercitoargentino.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/hello")
    public String test() {
        return "hello";
    }

    @GetMapping("/hello-secured")
    public String testSecured() {
        return "hello-secured";
    }

}
