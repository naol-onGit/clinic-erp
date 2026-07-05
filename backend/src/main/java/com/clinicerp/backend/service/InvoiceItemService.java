package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.InvoiceItem;

import java.util.List;
import java.util.Optional;

public interface InvoiceItemService {
    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    Optional<InvoiceItem> getInvoiceItemById(Long id);
    List<InvoiceItem> getAllInvoiceItems();
    InvoiceItem updateInvoiceItem(Long id, InvoiceItem invoiceItem);
    void deleteInvoiceItem(Long id);
}