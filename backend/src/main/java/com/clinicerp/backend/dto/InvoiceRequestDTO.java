package com.clinicerp.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequestDTO {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    private Long appointmentId;

    private BigDecimal totalAmount;

    @NotBlank(message = "Status is required")
    private String status;
}