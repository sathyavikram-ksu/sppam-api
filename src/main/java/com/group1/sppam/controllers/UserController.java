package com.group1.sppam.controllers;

import com.group1.sppam.controllers.exception.ResourceNotFoundException;
import com.group1.sppam.models.User;
import com.group1.sppam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("")
    Iterable<User> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<User> byId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    User newProject(@Valid @RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/{id}")
    User replaceEmployee(@RequestBody User updatedUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.update(updatedUser);
                    return repository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }
}
