package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.Invoice;
import com.clinicerp.backend.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    void createInvoice_shouldSaveAndReturnInvoice() {
        Invoice invoice = new Invoice();
        invoice.setTotalAmount(new BigDecimal("100.00"));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        Invoice result = invoiceService.createInvoice(invoice);

        assertEquals(new BigDecimal("100.00"), result.getTotalAmount());
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void getInvoiceById_shouldReturnInvoice_whenExists() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

        assertTrue(invoiceService.getInvoiceById(1L).isPresent());
    }

    @Test
    void getInvoiceById_shouldReturnEmpty_whenNotFound() {
        when(invoiceRepository.findById(999L)).thenReturn(Optional.empty());

        assertTrue(invoiceService.getInvoiceById(999L).isEmpty());
    }

    @Test
    void getAllInvoices_shouldReturnList() {
        when(invoiceRepository.findAll()).thenReturn(List.of(new Invoice(), new Invoice()));

        assertEquals(2, invoiceService.getAllInvoices().size());
    }

    @Test
    void deleteInvoice_shouldCallRepositoryDelete() {
        invoiceService.deleteInvoice(1L);
        verify(invoiceRepository, times(1)).deleteById(1L);
    }
}