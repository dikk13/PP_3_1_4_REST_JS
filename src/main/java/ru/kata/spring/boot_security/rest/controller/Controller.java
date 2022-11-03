package ru.kata.spring.boot_security.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.rest.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.rest.model.User;
import ru.kata.spring.boot_security.rest.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api")
public class Controller {

    private final UserService userService;
    @Autowired
    public Controller(UserService userService) {this.userService = userService;}

    @GetMapping("/users")
    public List<User> users() {
        return userService.listUsers();
    }

    @GetMapping("/authUser")
    public User getAuthUser(@AuthenticationPrincipal User currentUser) {
        return userService.getUser(currentUser.getId());
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        return user;
    }
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.getUser(id);
        userService.delete(id);
    }
}
