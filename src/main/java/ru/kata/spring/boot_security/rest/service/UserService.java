package ru.kata.spring.boot_security.rest.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.rest.model.Role;
import ru.kata.spring.boot_security.rest.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void deleteUser(int id);
    User getUserById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
}
