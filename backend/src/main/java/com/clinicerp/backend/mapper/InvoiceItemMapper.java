package com.clinicerp.backend.mapper;

import com.clinicerp.backend.dto.InvoiceItemRequestDTO;
import com.clinicerp.backend.dto.InvoiceItemResponseDTO;
import com.clinicerp.backend.entity.InvoiceItem;

public class InvoiceItemMapper {

    public static InvoiceItem toEntity(InvoiceItemRequestDTO dto) {
        InvoiceItem item = new InvoiceItem();
        item.setInvoiceId(dto.getInvoiceId());
        item.setDescription(dto.getDescription());
        item.setAmount(dto.getAmount());
        return item;
    }

    public static InvoiceItemResponseDTO toResponseDTO(InvoiceItem item) {
        InvoiceItemResponseDTO dto = new InvoiceItemResponseDTO();
        dto.setId(item.getId());
        dto.setInvoiceId(item.getInvoiceId());
        dto.setDescription(item.getDescription());
        dto.setAmount(item.getAmount());
        return dto;
    }
}