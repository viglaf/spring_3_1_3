package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);
    void addUser(User user);
    User getUserById(long id);
    void updateUser(User user);
    void removeUserById(long id);
    List<User> listUsers();
}
