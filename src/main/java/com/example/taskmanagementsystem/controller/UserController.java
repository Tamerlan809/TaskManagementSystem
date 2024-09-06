package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Method to show create user form
    @GetMapping("/create")
    public String showCreateUserForm(Model model){
        model.addAttribute("user", new User());
        return "createUser.html"; //Thymeleaf template name
    }

    //Method to handle create user form
    @PostMapping
    public String createUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/"; //redirecting to the home page(its temporary)
    }

    //Method to show list of users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public String listUsers(Model model){
        List<User> users = userService.findAllUsers();
        users.forEach(user -> {
            String roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(", "));
            user.setRoleNames(roles);
        });
        model.addAttribute("users", users);
        return "showUsers.html";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/users/list";
    }
}
