package ru.kata.spring.boot_security.demo.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findRolesByIdIn(Collection<Integer> id);
}
