package ru.kata.spring.boot_security.rest.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.rest.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = ?1")
    User findUserByEmail(String email);

}
