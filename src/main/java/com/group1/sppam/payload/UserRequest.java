package com.group1.sppam.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequest {
    @NotBlank
    @Size(min = 1, max = 255, message = "Please provide name with 1-255 characters")
    private String name;

    @NotBlank
    @Email(message = "Please provide valid email")
    private String email;

    private String password;

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
}
