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

   @Column(name = "age", nullable = false)
   private int age;

   @Column(nullable = false, length = 64)
   private String password;

   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @ManyToMany
   @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {}

   public User(String firstname, String lastname, int age, String password, String email, Set<Role> roles) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.age = age;
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

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String getUsername() {return email;}

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
      if (!firstname.equals(user.firstname)) return false;
      if (!lastname.equals(user.lastname)) return false;
      if (!(age == (user.age))) return false;
      if (!email.equals(user.email)) return false;
      if (!password.equals(user.password)) return false;
      return Objects.equals(roles, user.roles);
   }

   @Override
   public int hashCode() {
      int result = id/100;
      result = 31 * result + firstname.hashCode();
      result = 31 * result + lastname.hashCode();
      result = age/100 + result;
      result = 31 * result + email.hashCode();
      result = 31 * result + password.hashCode();
      result = 31 * result + (roles != null ? roles.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return String.format("id : %d,\nfirstname : %s,\nlastname : %s,\nage : %d,\n" +
              "password : %s,\nemail : %s,\nroles : %s",getId(),getFirstname(),getLastname(),getAge(),
               getPassword(),getEmail(),getRoles());
   }
}
