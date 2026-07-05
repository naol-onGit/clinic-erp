package com.clinicerp.backend.mapper;

import com.clinicerp.backend.dto.InvoiceRequestDTO;
import com.clinicerp.backend.dto.InvoiceResponseDTO;
import com.clinicerp.backend.entity.Invoice;

public class InvoiceMapper {

    public static Invoice toEntity(InvoiceRequestDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setPatientId(dto.getPatientId());
        invoice.setAppointmentId(dto.getAppointmentId());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setStatus(dto.getStatus());
        return invoice;
    }

    public static InvoiceResponseDTO toResponseDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setPatientId(invoice.getPatientId());
        dto.setAppointmentId(invoice.getAppointmentId());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setStatus(invoice.getStatus());
        dto.setCreatedAt(invoice.getCreatedAt());
        return dto;
    }
}