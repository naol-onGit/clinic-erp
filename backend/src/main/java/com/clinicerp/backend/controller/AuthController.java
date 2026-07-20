package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.AuthResponseDTO;
import com.clinicerp.backend.dto.LoginRequestDTO;
import com.clinicerp.backend.dto.RegisterRequestDTO;
import com.clinicerp.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(@Valid @RequestBody RegisterRequestDTO request) {
        System.out.println("REGISTER ENDPOINT HIT");
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}