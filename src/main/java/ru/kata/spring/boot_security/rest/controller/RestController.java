package ru.kata.spring.boot_security.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.rest.model.User;
import ru.kata.spring.boot_security.rest.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api")
public class RestController {

    private final UserService userService;
    @Autowired
    public RestController(UserService userService) {this.userService = userService;}

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/authUser")
    public User getAuthUser(@AuthenticationPrincipal User currentUser) {
        return userService.getUserById(currentUser.getId());
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user;
    }
    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
    @PatchMapping("/users/{id}")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.getUserById(id);
        userService.deleteUser(id);
    }
}
