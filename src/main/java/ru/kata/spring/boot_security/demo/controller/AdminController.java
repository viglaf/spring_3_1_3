package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public String findAll(Principal principal, Model model) {
        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        List<User> users = userServiceImpl.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/user-save")
    public String saveUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImpl.deleteById(id);
        return "redirect:/admin";
    }
}
