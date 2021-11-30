package com.nixsolution.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true, name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(unique = true, name = "email")
    private String email;

    @NotNull
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NotNull
    @Column(name = "LASTNAME")
    private String lastName;

    @NotNull
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "ROLEID", referencedColumnName = "ID")
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login='" + login + '\''
                + ", password='" + password + '\'' + ", email='" + email + '\''
                + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
                + '\'' + ", birthday=" + birthday + ", role=" + role + '}';
    }
}


