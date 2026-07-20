package com.clinicerp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseDTO {
    private Long id;
    private Long patientId;
    private Long appointmentId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
}