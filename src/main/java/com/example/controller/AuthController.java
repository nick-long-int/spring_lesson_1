package com.example.controller;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest authRequest) {
        return authService.signUp(authRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
            .authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(authService.generateToken(authRequest.getUsername()));
            return authResponse;
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @GetMapping("/validate")
    public boolean validate(@RequestParam("token") String token) {
        return authService.validateToken(token);
    }

}
