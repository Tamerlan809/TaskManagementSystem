package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.service.TaskService;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    //Method to show the form
    @GetMapping("/create")
    public String showCreateTaskForm(Model model) {
        List<User> users = userService.findAllUsers(); // Get all users
        model.addAttribute("task", new Task());
        model.addAttribute("users", users);
        return "createTask.html";  // Thymeleaf template
    }

    //Method to post the form submission
    @PostMapping
    public String createTask(@ModelAttribute Task task){
        Task savedTask = taskService.createTask(task);
        return "redirect:/"; //Redirecting to home page(Temporary)
    }

    //Method to display tasks to dashboard
    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        List<Task> tasks = taskService.findAllTask(); //fetches all tasks
        model.addAttribute("tasks", tasks);
        return "dashboard.html";
    }

    //Method to delete a task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return "redirect:/tasks/dashboard";
    }
}
