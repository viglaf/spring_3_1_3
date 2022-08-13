package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String findAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @GetMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-update{id}")
    public String updateUserForm(@PathVariable ("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping ("/user-delete{id}")
    public String userDelete (@PathVariable ("id") int id){
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
