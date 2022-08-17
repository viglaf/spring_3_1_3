package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String userInfoPage(Principal principal, Model model) {
        User user = userService.findByName(principal.getName());
        for (Role el : user.getRoles()) {
            if(el.getName().equals("ROLE_ADMIN")) {
                model.addAttribute("role", "ADMIN");
                break;
            }
            if (el.getName().equals("ROLE_USER")){
                model.addAttribute("role", "USER");
                break;
            }
        }
        model.addAttribute("user", user);
        return "user";
    }
}
