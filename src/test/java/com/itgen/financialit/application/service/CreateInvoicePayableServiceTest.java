package com.itgen.financialit.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itgen.financialit.application.port.out.invoice.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.application.port.out.supplier.CreateSupplierRepositoryPort;
import com.itgen.financialit.application.service.invoice.CreateInvoicePayableService;
import com.itgen.financialit.domain.exception.invoice.InvoiceAlreadyCreatedException;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;
import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

@ExtendWith(MockitoExtension.class)
class CreateInvoicePayableServiceTest {

    @Mock
    private CreateInvoicePayableRepositoryPort invoiceRepository;

    @Mock
    private CreateSupplierRepositoryPort supplierRepository;

    @InjectMocks
    private CreateInvoicePayableService service;

    private Supplier supplier;
    private InvoicePayable invoicePayable;

    @BeforeEach
    void setup() {
        supplier = new Supplier(1L, "Fornecedor Teste");

        invoicePayable = new InvoicePayable(
            "Conta de Luz",
            new BigDecimal("250.00"),
            LocalDate.now().plusDays(10),
            Category.OTHER,
            Status.PENDING,
            supplier
        );
    }

    @Test
    void shouldCreateInvoicePayableSuccessfully() {
        // arrange
        when(supplierRepository.findById(1L))
            .thenReturn(Optional.of(supplier));

        when(invoiceRepository.save(any(InvoicePayable.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // act
        InvoicePayable result = service.createInvoicePayable(invoicePayable);

        // assert
        assertNotNull(result);
        assertEquals("Conta de Luz", result.getDescription());
        assertEquals(supplier, result.getSupplier());

        verify(supplierRepository).findById(1L);
        verify(invoiceRepository).save(any(InvoicePayable.class));
    }

    @Test
    void shouldThrowExceptionWhenSupplierDoesNotExist() {
        // arrange
        when(supplierRepository.findById(1L))
            .thenReturn(Optional.empty());

        // act & assert
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> service.createInvoicePayable(invoicePayable)
        );

        assertEquals("Supplier was not created", exception.getMessage());

        verify(invoiceRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenInvoiceAlreadyHasId() {
        // arrange
        InvoicePayable invoiceWithId = new InvoicePayable(
            "Conta de Água",
            new BigDecimal("120.00"),
            LocalDate.now().plusDays(5),
            Category.OTHER,
            Status.PENDING,
            supplier
        );

        invoiceWithId.setId(10L);

        when(supplierRepository.findById(1L))
            .thenReturn(Optional.of(supplier));

        // act & assert
        InvoiceAlreadyCreatedException exception = assertThrows(
            InvoiceAlreadyCreatedException.class,
            () -> service.createInvoicePayable(invoiceWithId)
        );

        assertEquals(
            " Conta já criada para este ID",
            exception.getMessage()
        );

        verify(invoiceRepository, never()).save(any());
    }
}
