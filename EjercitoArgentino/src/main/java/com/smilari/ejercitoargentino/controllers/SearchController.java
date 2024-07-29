package com.smilari.ejercitoargentino.controllers;

import com.smilari.ejercitoargentino.entities.UserEntity;
import com.smilari.ejercitoargentino.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private UserService userService;

    @GetMapping
    public String searchUsers(@RequestParam(value = "searchQuery", required = false) String searchQuery, Model model) {
        List<UserEntity> users = userService.searchUsers(searchQuery);
        model.addAttribute("users", users);
        return "search";
    }
}
