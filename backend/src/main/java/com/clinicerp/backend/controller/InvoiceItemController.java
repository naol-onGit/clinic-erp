package com.clinicerp.backend.controller;

import com.clinicerp.backend.dto.InvoiceItemRequestDTO;
import com.clinicerp.backend.dto.InvoiceItemResponseDTO;
import com.clinicerp.backend.entity.InvoiceItem;
import com.clinicerp.backend.mapper.InvoiceItemMapper;
import com.clinicerp.backend.service.InvoiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-items")
@RequiredArgsConstructor
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @PostMapping
    public ResponseEntity<InvoiceItemResponseDTO> createInvoiceItem(@RequestBody InvoiceItemRequestDTO requestDTO) {
        InvoiceItem item = InvoiceItemMapper.toEntity(requestDTO);
        InvoiceItem saved = invoiceItemService.createInvoiceItem(item);
        return ResponseEntity.status(201).body(InvoiceItemMapper.toResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceItemResponseDTO>> getAllInvoiceItems() {
        List<InvoiceItemResponseDTO> result = invoiceItemService.getAllInvoiceItems().stream()
                .map(InvoiceItemMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItemResponseDTO> getInvoiceItemById(@PathVariable Long id) {
        return invoiceItemService.getInvoiceItemById(id)
                .map(InvoiceItemMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItemResponseDTO> updateInvoiceItem(@PathVariable Long id, @RequestBody InvoiceItemRequestDTO requestDTO) {
        InvoiceItem item = InvoiceItemMapper.toEntity(requestDTO);
        InvoiceItem updated = invoiceItemService.updateInvoiceItem(id, item);
        return ResponseEntity.ok(InvoiceItemMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceItem(@PathVariable Long id) {
        invoiceItemService.deleteInvoiceItem(id);
        return ResponseEntity.noContent().build();
    }
}