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
public class InvoiceItemRequestDTO {

    @NotNull(message = "Invoice ID is required")
    private Long invoiceId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;
}