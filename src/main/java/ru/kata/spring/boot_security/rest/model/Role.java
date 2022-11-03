package ru.kata.spring.boot_security.rest.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return roleName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String roleName;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(int id, String role) {
        this.id = id;
        this.roleName = role;
    }

    public String getNoPrefix() {
        String pr = "ROLE_";
        return roleName.substring(pr.length());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() { return roleName; }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    @Override
    public String toString() {
        return "role - " + roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        if (!(id == role.getId())) return false;
        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        int result = id/100;
        result = 31 * result + roleName.hashCode();
        return result;
    }
}
