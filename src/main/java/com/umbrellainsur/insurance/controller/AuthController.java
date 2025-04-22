package com.umbrellainsur.insurance.controller;

import com.umbrellainsur.insurance.model.Broker;
import com.umbrellainsur.insurance.repository.BrokerRepository;
import com.umbrellainsur.insurance.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final BrokerRepository brokerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (brokerRepository.existsByEmail(signupRequest.email())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Email is already in use!"));
        }

        // Create new broker
        Broker broker = new Broker();
        broker.setName(signupRequest.name());
        broker.setEmail(signupRequest.email());
        broker.setPassword(passwordEncoder.encode(signupRequest.password()));

        // Save broker
        brokerRepository.save(broker);

        // Generate JWT token
        String token = jwtUtil.generateToken(broker.getEmail());

        // Return success response with token
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        response.put("token", token);
        response.put("user", Map.of(
                "id", broker.getId(),
                "name", broker.getName(),
                "email", broker.getEmail()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );

            Broker broker = (Broker) authentication.getPrincipal();


            String token = jwtUtil.generateToken(broker.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "id", broker.getId(),
                    "name", broker.getName(),
                    "email", broker.getEmail()
            ));

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }
    }


    public record SignupRequest(String name, String email, String password) {}
    public record LoginRequest(String email, String password) {}
}