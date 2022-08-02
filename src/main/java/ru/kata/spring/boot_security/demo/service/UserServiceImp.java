package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImp implements UserService{

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   public PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
   }

   @Transactional
   @Override
   public void saveUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
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
      user.setPassword(passwordEncoder.encode(user.getPassword()));
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

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findUserByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));
      return user;
   }

//   private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//      String[] userRoles = user.getRoles().stream().map(Role::getRoleName).toArray(String[]::new);
//      Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//      return authorities;
//   }
}
