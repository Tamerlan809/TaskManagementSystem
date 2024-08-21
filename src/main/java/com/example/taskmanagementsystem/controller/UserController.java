package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Method to show form
    @GetMapping("/create")
    public String showCreateUserForm(Model model){
        model.addAttribute("user", new User());
        return "createUser.html"; //Thymeleaf template name
    }

    //Method to handle form
    @PostMapping
    public String createUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/"; //redirecting to the home page(its temporary)
    }
}
