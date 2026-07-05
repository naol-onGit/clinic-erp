package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.InvoiceItem;
import com.clinicerp.backend.repository.InvoiceItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public Optional<InvoiceItem> getInvoiceItemById(Long id) {
        return invoiceItemRepository.findById(id);
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public InvoiceItem updateInvoiceItem(Long id, InvoiceItem invoiceItem) {
        invoiceItem.setId(id);
        return invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public void deleteInvoiceItem(Long id) {
        invoiceItemRepository.deleteById(id);
    }
}