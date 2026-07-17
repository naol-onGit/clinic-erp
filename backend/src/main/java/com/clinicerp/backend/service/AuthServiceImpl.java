package com.clinicerp.backend.service;

import com.clinicerp.backend.dto.AuthResponseDTO;
import com.clinicerp.backend.dto.LoginRequestDTO;
import com.clinicerp.backend.dto.RegisterRequestDTO;
import com.clinicerp.backend.entity.User;
import com.clinicerp.backend.repository.UserRepository;
import com.clinicerp.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists.");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new AuthResponseDTO(
                token,
                user.getUsername(),
                user.getRole()
        );
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new RuntimeException("Invalid username or password.");
        }

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new AuthResponseDTO(
                token,
                user.getUsername(),
                user.getRole()
        );
    }
}