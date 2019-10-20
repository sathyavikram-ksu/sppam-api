package com.group1.sppam.controllers;

import com.group1.sppam.exception.ResourceNotFoundException;
import com.group1.sppam.models.User;
import com.group1.sppam.payload.ApiResponse;
import com.group1.sppam.payload.JwtAuthenticationResponse;
import com.group1.sppam.payload.LoginRequest;
import com.group1.sppam.payload.UserRequest;
import com.group1.sppam.repository.UserRepository;
import com.group1.sppam.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}
