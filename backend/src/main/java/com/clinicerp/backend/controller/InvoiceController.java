package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.InvoiceRequestDTO;
import com.clinicerp.backend.dto.InvoiceResponseDTO;
import com.clinicerp.backend.entity.Invoice;
import com.clinicerp.backend.mapper.InvoiceMapper;
import com.clinicerp.backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody InvoiceRequestDTO requestDTO) {
        Invoice invoice = InvoiceMapper.toEntity(requestDTO);
        Invoice saved = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(201).body(InvoiceMapper.toResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        List<InvoiceResponseDTO> result = invoiceService.getAllInvoices().stream()
                .map(InvoiceMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(InvoiceMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequestDTO requestDTO) {
        Invoice invoice = InvoiceMapper.toEntity(requestDTO);
        Invoice updated = invoiceService.updateInvoice(id, invoice);
        return ResponseEntity.ok(InvoiceMapper.toResponseDTO(updated));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}