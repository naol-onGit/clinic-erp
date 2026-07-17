package com.clinicerp.backend.service;

import com.clinicerp.backend.dto.AuthResponseDTO;
import com.clinicerp.backend.dto.LoginRequestDTO;
import com.clinicerp.backend.dto.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}