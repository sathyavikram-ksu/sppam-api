package com.group1.sppam.controllers;

import com.group1.sppam.exception.ResourceNotFoundException;
import com.group1.sppam.models.User;
import com.group1.sppam.payload.UserRequest;
import com.group1.sppam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    Iterable<User> all() {
        return repository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    User newProject(@Valid @RequestBody UserRequest userRequest) {
        User newUser = new User(userRequest);
        if (newUser.getPassword() != null && newUser.getPassword().trim().length() > 1) {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
        return repository.save(newUser);
    }

    @GetMapping("/{id}")
    Optional<User> byId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    User replaceEmployee(@RequestBody UserRequest updatedUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.update(updatedUser);
                    if (updatedUser.getPassword() != null && updatedUser.getPassword().trim().length() > 1) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                    return repository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }
}
