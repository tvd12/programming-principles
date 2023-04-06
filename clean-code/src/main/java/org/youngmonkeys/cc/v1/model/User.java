package org.youngmonkeys.cc.v1.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.youngmonkeys.cc.v1.model.audit.DateAudit;

@Entity(name = "USER")
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean active;

    private Set<Role> roles = new HashSet<>();

    private Boolean isEmailVerified;

    public User() {
        super();
    }

    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        active = user.getActive();
        roles = user.getRoles();
        isEmailVerified = user.getEmailVerified();
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUserList().add(this);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUserList().remove(this);
    }

    public void markVerificationConfirmed() {
        setEmailVerified(true);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> authorities) {
        roles = authorities;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", username='" + username + '\'' + ", password='"
               + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", active="
               + active + ", roles=" + roles + ", isEmailVerified=" + isEmailVerified + '}';
    }
}
