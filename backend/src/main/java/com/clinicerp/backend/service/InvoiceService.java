package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    Optional<Invoice> getInvoiceById(Long id);
    List<Invoice> getAllInvoices();
    Invoice updateInvoice(Long id, Invoice invoice);
    void deleteInvoice(Long id);
}