package com.smilari.ejercitoargentino.controllers;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest, Model model) {
        Map<String, Object> errorDetails = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAttribute("status", errorDetails.get("status"));
        model.addAttribute("message", errorDetails.get("message"));
        return "error";
    }
}