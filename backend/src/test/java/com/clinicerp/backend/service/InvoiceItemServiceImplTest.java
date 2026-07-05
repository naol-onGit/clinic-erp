package com.clinicerp.backend.service;

import com.clinicerp.backend.entity.InvoiceItem;
import com.clinicerp.backend.repository.InvoiceItemRepository;
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
class InvoiceItemServiceImplTest {

    @Mock
    private InvoiceItemRepository invoiceItemRepository;

    @InjectMocks
    private InvoiceItemServiceImpl invoiceItemService;

    @Test
    void createInvoiceItem_shouldSaveAndReturnItem() {
        InvoiceItem item = new InvoiceItem();
        item.setDescription("Consultation fee");
        item.setAmount(new BigDecimal("500.00"));
        when(invoiceItemRepository.save(any(InvoiceItem.class))).thenReturn(item);

        InvoiceItem result = invoiceItemService.createInvoiceItem(item);

        assertEquals("Consultation fee", result.getDescription());
        verify(invoiceItemRepository, times(1)).save(item);
    }

    @Test
    void getInvoiceItemById_shouldReturnItem_whenExists() {
        InvoiceItem item = new InvoiceItem();
        item.setId(1L);
        when(invoiceItemRepository.findById(1L)).thenReturn(Optional.of(item));

        assertTrue(invoiceItemService.getInvoiceItemById(1L).isPresent());
    }

    @Test
    void getInvoiceItemById_shouldReturnEmpty_whenNotFound() {
        when(invoiceItemRepository.findById(999L)).thenReturn(Optional.empty());

        assertTrue(invoiceItemService.getInvoiceItemById(999L).isEmpty());
    }

    @Test
    void getAllInvoiceItems_shouldReturnList() {
        when(invoiceItemRepository.findAll()).thenReturn(List.of(new InvoiceItem(), new InvoiceItem()));

        assertEquals(2, invoiceItemService.getAllInvoiceItems().size());
    }

    @Test
    void deleteInvoiceItem_shouldCallRepositoryDelete() {
        invoiceItemService.deleteInvoiceItem(1L);
        verify(invoiceItemRepository, times(1)).deleteById(1L);
    }
}