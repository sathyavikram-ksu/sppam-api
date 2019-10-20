package com.group1.sppam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group1.sppam.payload.UserRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Please provide valid name")
    @Size(min = 1, max = 255, message = "Please provide name with 1-255 characters")
    private String name;

    @Email(message = "Please provide valid email")
    private String email;

    @JsonIgnore
    private String password;

    public User() {
    }

    public User(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

    public User(@NotEmpty(message = "Please provide valid name") @Size(min = 1, max = 255, message = "Please provide name with 1-255 characters") String name, @Email(message = "Please provide valid email") String email, @NotEmpty(message = "Please provide valid password") String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update(UserRequest updatedUser) {
        this.setName(updatedUser.getName());
        this.setEmail(updatedUser.getEmail());
    }
}
