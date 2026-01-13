package com.itgen.financialit.application.service.invoice;


import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.invoice.CreateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.invoice.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.application.port.out.supplier.CreateSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.invoice.InvoiceAlreadyCreatedException;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;
import com.itgen.financialit.domain.model.enums.Status;

import jakarta.transaction.Transactional;

@Service
public class CreateInvoicePayableService implements CreateInvoicePayableUseCase{


    private final CreateInvoicePayableRepositoryPort repository;
    private final CreateSupplierRepositoryPort repositorySupplier;
    private final KafkaTemplate<String, InvoicePayable> kafkaTemplateOrder;


    public CreateInvoicePayableService(
        CreateInvoicePayableRepositoryPort repository,
        CreateSupplierRepositoryPort repositorySupplier,
        KafkaTemplate<String,InvoicePayable> kafkaTemplateOrder
    ){
        this.repository = repository;
        this.repositorySupplier = repositorySupplier;
        this.kafkaTemplateOrder = kafkaTemplateOrder;  
    }

    @Override
    @Transactional
    @SuppressWarnings("null")
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
        kafkaTemplateOrder.send("Invoice-payable-created-order-processed", invoiceCreated);
        return invoiceCreated;
    }

}
