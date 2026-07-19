package com.clinicerp.backend.service;

import com.clinicerp.backend.dto.AuthResponseDTO;
import com.clinicerp.backend.dto.LoginRequestDTO;
import com.clinicerp.backend.dto.RegisterRequestDTO;
import com.clinicerp.backend.entity.User;
import com.clinicerp.backend.repository.UserRepository;
import com.clinicerp.backend.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void register_shouldHashPasswordAndReturnToken() {
        RegisterRequestDTO request = new RegisterRequestDTO("newuser", "rawpass", "ADMIN");

        User savedUser = new User();
        savedUser.setUsername("newuser");
        savedUser.setRole("ADMIN");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("rawpass")).thenReturn("hashedpass");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtUtil.generateToken("newuser", "ADMIN")).thenReturn("fake-jwt-token");

        AuthResponseDTO result = authService.register(request);

        assertEquals("fake-jwt-token", result.getToken());
        assertEquals("newuser", result.getUsername());
        verify(passwordEncoder, times(1)).encode("rawpass");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_shouldThrowException_whenUsernameAlreadyExists() {
        RegisterRequestDTO request = new RegisterRequestDTO("existinguser", "rawpass", "ADMIN");

        User existingUser = new User();
        existingUser.setUsername("existinguser");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(existingUser));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                authService.register(request));

        assertEquals("Username already exists: existinguser", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_shouldReturnToken_whenCredentialsValid() {
        LoginRequestDTO request = new LoginRequestDTO("existinguser", "rawpass");

        User user = new User();
        user.setUsername("existinguser");
        user.setPasswordHash("hashedpass");
        user.setRole("RECEPTIONIST");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("rawpass", "hashedpass")).thenReturn(true);
        when(jwtUtil.generateToken("existinguser", "RECEPTIONIST")).thenReturn("fake-jwt-token");

        AuthResponseDTO result = authService.login(request);

        assertEquals("fake-jwt-token", result.getToken());
        assertEquals("existinguser", result.getUsername());
        verify(passwordEncoder, times(1)).matches("rawpass", "hashedpass");
    }

    @Test
    void login_shouldThrowException_whenPasswordIsWrong() {
        LoginRequestDTO request = new LoginRequestDTO("existinguser", "wrongpass");

        User user = new User();
        user.setUsername("existinguser");
        user.setPasswordHash("hashedpass");
        user.setRole("RECEPTIONIST");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpass", "hashedpass")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                authService.login(request));

        assertEquals("Invalid username or password.", exception.getMessage());
    }

    @Test
    void login_shouldThrowException_whenUserNotFound() {
        LoginRequestDTO request = new LoginRequestDTO("unknownuser", "rawpass");

        when(userRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                authService.login(request));

        assertEquals("Invalid username or password.", exception.getMessage());
    }
}