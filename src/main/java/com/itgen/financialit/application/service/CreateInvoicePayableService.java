package com.itgen.financialit.application.service;



import org.springframework.stereotype.Service;
import com.itgen.financialit.application.port.in.CreateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.application.port.out.CreateSupplierRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;

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
            throw new IllegalStateException("Invoice already has an ID and cannot be created");
        }
        
        InvoicePayable invoiceToSave = new InvoicePayable(
            invoicePayable.getDescription(),
            invoicePayable.getAmount(),
            invoicePayable.getDueDate(),
            invoicePayable.getCategory(),
            supplier
        );

        InvoicePayable invoiceCreated = repository.save(invoiceToSave);
        return invoiceCreated;
    }

}
