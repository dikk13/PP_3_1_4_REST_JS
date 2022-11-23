package ru.kata.spring.boot_security.rest.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.rest.model.Role;

import java.util.Collection;
import java.util.Set;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findRolesByIdIn(Collection<Integer> id);
}
