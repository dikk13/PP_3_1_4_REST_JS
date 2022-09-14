package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "first_name", nullable = false, length = 20)
   private String firstname;

   @Column(name = "last_name", nullable = false, length = 20)
   private String lastname;

   @Column(name = "user_name", nullable = false, length = 45)
   private String username;

   @Column(nullable = false, length = 64)
   private String password;

   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @ManyToMany
   @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {}

   public User(String username, String password, String email, Set<Role> roles) {
      this.username = username;
      this.password = password;
      this.email = email;
      this.roles = roles;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setUsername(String username) {this.username = username;}

   public String getUsername() {return username;}

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      if (!(id == (user.getId()))) return false;
      if (!username.equals(user.username)) return false;
      if (!email.equals(user.email)) return false;
      if (!password.equals(user.password)) return false;
      return Objects.equals(roles, user.roles);
   }

   @Override
   public int hashCode() {
      int result = id/100;
      result = 31 * result + username.hashCode();
      result = 31 * result + email.hashCode();
      result = 31 * result + password.hashCode();
      result = 31 * result + (roles != null ? roles.hashCode() : 0);
      return result;
   }

}
