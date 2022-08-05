package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void saveUser(User user);
    void delete(int id);
    void update(int id, User user);
    User getUser(int id);
    List<User> listUsers();
    User findByEmail(String email);
    void add(User user);
    List<Role> listRoles();
    Set<Role> getRolesByIdArr(int[] idList);
}
