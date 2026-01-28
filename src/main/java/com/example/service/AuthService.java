package com.example.service;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.kafka.KafkaStreamBridge;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repo.UserRepo;
import com.example.service.assymetrics.JwtProducer;
import com.example.service.assymetrics.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtValidator jwtValidator;
    private final JwtProducer jwtProducer;
    private final KafkaStreamBridge bridge;

    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED
    )
    public AuthResponse signUp(AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(UserRole.USER);

        User savedUser = userRepo.save(user);

        String token = jwtProducer.createToken(savedUser);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(savedUser.getUsername());
        authResponse.setId(savedUser.getId());
        authResponse.setToken(token);

        bridge.sendUserWasRegister("producer-out-0", authRequest.getUsername());
        return authResponse;
    }

    public String generateToken(String username) {
        User user = userRepo.findByUsername(username);
        return jwtProducer.createToken(user);
    }

    public boolean validateToken(String token) {
        return jwtValidator.validate(token);
    }
}
