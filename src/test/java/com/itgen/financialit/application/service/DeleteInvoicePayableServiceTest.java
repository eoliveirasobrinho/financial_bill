package com.itgen.financialit.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itgen.financialit.application.port.out.invoice.DeleteInvoicePayableRepositoryPort;
import com.itgen.financialit.application.service.invoice.DeleteInvoicePayableService;
import com.itgen.financialit.domain.model.InvoicePayable;

@ExtendWith(MockitoExtension.class)
class DeleteInvoicePayableServiceTest {

    @Mock
    private DeleteInvoicePayableRepositoryPort repository;

    @InjectMocks
    private DeleteInvoicePayableService service;

    private InvoicePayable invoice;

    @BeforeEach
    void setup() {
        invoice = new InvoicePayable();
        invoice.setId(1L);
    }

    // =========================
    // findById
    // =========================

    @Test
    @DisplayName("Deve retornar InvoicePayable quando ID existir")
    void shouldReturnInvoiceWhenIdExists() {
        when(repository.findById(1L)).thenReturn(invoice);

        InvoicePayable result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando ID retornado for nulo")
    void shouldThrowExceptionWhenReturnedIdIsNull() {
        invoice.setId(null);
        when(repository.findById(1L)).thenReturn(invoice);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.findById(1L)
        );

        assertEquals("ID nulo ou inexistente", exception.getMessage());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando ID retornado for diferente do solicitado")
    void shouldThrowExceptionWhenIdIsDifferent() {
        invoice.setId(2L);
        when(repository.findById(1L)).thenReturn(invoice);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.findById(1L)
        );

        assertEquals("ID nulo ou inexistente", exception.getMessage());
        verify(repository).findById(1L);
    }

    // =========================
    // deleteInvoicePayable
    // =========================

    @Test
    @DisplayName("Deve deletar InvoicePayable quando existir")
    void shouldDeleteInvoiceWhenExists() {
        when(repository.findById(1L)).thenReturn(invoice);
        doNothing().when(repository).deleteInvoicePayable(1L);

        service.deleteInvoicePayable(1L);

        verify(repository).findById(1L);
        verify(repository).deleteInvoicePayable(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar Invoice inexistente")
    void shouldThrowExceptionWhenDeletingNonExistingInvoice() {
        InvoicePayable invoiceInvalida = new InvoicePayable();
        invoiceInvalida.setId(null);

        when(repository.findById(1L)).thenReturn(invoiceInvalida);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.deleteInvoicePayable(1L)
        );

        assertEquals("ID nulo ou inexistente", exception.getMessage());
        verify(repository).findById(1L);
        verify(repository, never()).deleteInvoicePayable(anyLong());
    }
}
