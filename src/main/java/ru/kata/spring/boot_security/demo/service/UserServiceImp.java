package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.userRepository.RoleRepository;
import ru.kata.spring.boot_security.demo.userRepository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder(12);
   }

   @Autowired
   public UserServiceImp(UserRepository userRepository,
                         RoleRepository roleRepository) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
   }

   @Transactional
   @Override
   public void saveUser(User user) {
      user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
      userRepository.save(user);
   }

   @Transactional
   @Override
   public void delete(int id) {
      userRepository.deleteById(id);
   }

   @Transactional
   @Override
   public void update(int id, User user) {
      user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
      userRepository.save(user);
   }

   @Transactional
   @Override
   public User getUser(int id) {
      return userRepository.findById(id).orElse(null);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userRepository.findAll();
   }

   @Transactional
   public User findByEmail(String email){
      return userRepository.findUserByEmail(email);
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = userRepository.findUserByEmail(email);
      return user;
   }

   @Override
   public void add(User user) {
      userRepository.save(user);
   }

   @Override
   public List<Role> listRoles() {
      return roleRepository.findAll().stream().toList();
   }

   @Override
   public Set<Role> getRolesByIdArr(int[] idList) {
      Set<Role> result = new HashSet<>();
      for (int id : idList) {
         result.add(roleRepository.findById(id).get());
      }
      return result;
   }
}
