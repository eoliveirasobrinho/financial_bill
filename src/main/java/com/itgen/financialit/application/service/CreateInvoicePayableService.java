package com.itgen.financialit.application.service;


import org.springframework.stereotype.Service;
import com.itgen.financialit.application.port.in.CreateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.application.port.out.CreateSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.invoice.InvoiceAlreadyCreatedException;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;
import com.itgen.financialit.domain.model.enums.Status;

import jakarta.transaction.Transactional;

@Service
public class CreateInvoicePayableService implements CreateInvoicePayableUseCase{


    private final CreateInvoicePayableRepositoryPort repository;
    private final CreateSupplierRepositoryPort repositorySupplier;

    public CreateInvoicePayableService(
        CreateInvoicePayableRepositoryPort repository,
        CreateSupplierRepositoryPort repositorySupplier
    ){
        this.repository = repository;
        this.repositorySupplier = repositorySupplier;  
    }

    @Override
    @Transactional
    public InvoicePayable createInvoicePayable(InvoicePayable invoicePayable) {
        Supplier supplier = repositorySupplier.findById(invoicePayable.getSupplier().getId()).orElseThrow(() -> new IllegalStateException("Supplier was not created"));

        if(invoicePayable.getId() != null) {
            throw new InvoiceAlreadyCreatedException(" Conta já criada para este ID");
        }
        
        InvoicePayable invoiceToSave = new InvoicePayable(
            invoicePayable.getDescription(),
            invoicePayable.getAmount(),
            invoicePayable.getDueDate(),
            invoicePayable.getCategory(),
            Status.PENDING,
            supplier
        );

        InvoicePayable invoiceCreated = repository.save(invoiceToSave);
        return invoiceCreated;
    }

}
