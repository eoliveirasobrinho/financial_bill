package com.itgen.financialit.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itgen.financialit.application.port.out.UpdateInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;
import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

@ExtendWith(MockitoExtension.class)
class UpdateInvoicePayableServiceTest {

    @Mock
    private UpdateInvoicePayableRepositoryPort repositoryPort;

    @InjectMocks
    private UpdateInvoicePayableService service;

    private InvoicePayable existingInvoice;
    private InvoicePayable updatedInvoice;
    private Supplier supplier;

    @BeforeEach
    void setUp() {

        supplier = new Supplier();
        supplier.setId(1L);
        supplier.setDocument("324637247382");
        supplier.setEmail("testeSuppplier@test.com");
        supplier.setName("CLARO");

        existingInvoice = new InvoicePayable();
        existingInvoice.setId(1L);
        existingInvoice.setDescription("Internet");
        existingInvoice.setAmount(BigDecimal.valueOf(100));
        existingInvoice.setDueDate(LocalDate.now().plusDays(10));
        existingInvoice.setPaymentDate(null);
        existingInvoice.setStatus(Status.PAID);
        existingInvoice.setCategory(Category.TELEPHONE);
        existingInvoice.setSupplier(supplier);

        updatedInvoice = new InvoicePayable();
        updatedInvoice.setId(1L);
        updatedInvoice.setDescription("Internet fibra");
        updatedInvoice.setAmount(BigDecimal.valueOf(150));
        updatedInvoice.setDueDate(LocalDate.now().plusDays(15));
        updatedInvoice.setPaymentDate(LocalDate.now());
        updatedInvoice.setStatus(Status.PAID);
        updatedInvoice.setCategory(Category.INTERNET);
        updatedInvoice.setSupplier(supplier);

        
    }

    // =========================
    // CENÁRIO DE SUCESSO
    // =========================

    @Test
    void shouldUpdateInvoiceSuccessfully() {
        when(repositoryPort.findById(1L)).thenReturn(existingInvoice);
        when(repositoryPort.update(any(InvoicePayable.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        InvoicePayable result = service.updateInvoicePayable(updatedInvoice);

        assertNotNull(result);
        assertEquals("Internet fibra", result.getDescription());
        assertEquals(BigDecimal.valueOf(150), result.getAmount());
        assertEquals(LocalDate.now().plusDays(15), result.getDueDate());
        assertEquals(LocalDate.now(), result.getPaymentDate());
        assertEquals(Status.PAID, result.getStatus());
        assertEquals(Category.INTERNET, result.getCategory());
        assertEquals(supplier, result.getSupplier());

        verify(repositoryPort).findById(1L);
        verify(repositoryPort).update(existingInvoice);
    }

    @Test
    void shouldUpdateAllFields() {
        when(repositoryPort.findById(1L)).thenReturn(existingInvoice);
        when(repositoryPort.update(any())).thenReturn(existingInvoice);

        service.updateInvoicePayable(updatedInvoice);

        assertAll(
            () -> assertEquals(updatedInvoice.getDescription(), existingInvoice.getDescription()),
            () -> assertEquals(updatedInvoice.getAmount(), existingInvoice.getAmount()),
            () -> assertEquals(updatedInvoice.getDueDate(), existingInvoice.getDueDate()),
            () -> assertEquals(updatedInvoice.getPaymentDate(), existingInvoice.getPaymentDate()),
            () -> assertEquals(updatedInvoice.getStatus(), existingInvoice.getStatus()),
            () -> assertEquals(updatedInvoice.getCategory(), existingInvoice.getCategory()),
            () -> assertEquals(updatedInvoice.getSupplier(), existingInvoice.getSupplier())
        );
    }

    @Test
    void shouldCallUpdateRepositoryOnce() {
        when(repositoryPort.findById(1L)).thenReturn(existingInvoice);
        when(repositoryPort.update(any())).thenReturn(existingInvoice);

        service.updateInvoicePayable(updatedInvoice);

        verify(repositoryPort, times(1)).update(existingInvoice);
    }

    // =========================
    // CENÁRIOS DE ERRO
    // =========================

    @Test
    void shouldThrowExceptionWhenIdNotFound() {
        when(repositoryPort.findById(1L)).thenReturn(null);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.updateInvoicePayable(updatedInvoice)
        );

        assertEquals("id não encontrada", exception.getMessage());

        verify(repositoryPort).findById(1L);
        verify(repositoryPort, never()).update(any());
    }

    @Test
    void shouldNotCallUpdateWhenInvoiceDoesNotExist() {
        when(repositoryPort.findById(1L)).thenReturn(null);

        try {
            service.updateInvoicePayable(updatedInvoice);
        } catch (IllegalStateException ignored) {}

        verify(repositoryPort, never()).update(any());
    }
}
