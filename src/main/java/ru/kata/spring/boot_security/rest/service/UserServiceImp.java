package ru.kata.spring.boot_security.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.rest.model.User;
import ru.kata.spring.boot_security.rest.userRepository.RoleRepository;
import ru.kata.spring.boot_security.rest.userRepository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   public PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository,
                         RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Transactional
   @Override
   public void deleteUser(int id) {
      userRepository.deleteById(id);
   }

   @Override
   public User getUserById(int id) {
      return userRepository.findById(id).orElse(null);
   }

   @Override
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return userRepository.findUserByEmail(email);
   }
   @Transactional
   @Override
   public void saveUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }
}
