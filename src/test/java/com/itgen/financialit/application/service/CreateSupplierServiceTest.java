package com.itgen.financialit.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itgen.financialit.application.port.out.CreateSupplierRepositoryPort;
import com.itgen.financialit.application.service.supplier.CreateSupplierService;
import com.itgen.financialit.domain.model.Supplier;

@ExtendWith(MockitoExtension.class)
class CreateSupplierServiceTest {

    @Mock
    private CreateSupplierRepositoryPort repository;

    @InjectMocks
    private CreateSupplierService service;

    @Test
    void shouldCreateSupplierSuccessfully() {
        // arrange
        Supplier supplier = new Supplier();
        supplier.setName("Fornecedor Teste");

        when(repository.save(supplier))
            .thenReturn(supplier);

        // act
        Supplier result = service.createSupplier(supplier);

        // assert
        assertNotNull(result);
        assertEquals("Fornecedor Teste", result.getName());

        verify(repository).save(supplier);
    }

    @Test
    void shouldThrowExceptionWhenSupplierAlreadyExists() {
        // arrange
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Fornecedor Existente");

        // act & assert
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> service.createSupplier(supplier)
        );

        assertEquals("Supplier Already Exists ", exception.getMessage());

        verify(repository, never()).save(any());
    }
}
