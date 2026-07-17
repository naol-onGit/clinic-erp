package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.AuthResponseDTO;
import com.clinicerp.backend.dto.LoginRequestDTO;
import com.clinicerp.backend.dto.RegisterRequestDTO;
import com.clinicerp.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}