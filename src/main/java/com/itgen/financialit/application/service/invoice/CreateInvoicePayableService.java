package com.itgen.financialit.application.service.invoice;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.invoice.CreateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.invoice.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.application.port.out.metrics.invoice.InvoicePayableCreatedMetricsPort;
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
    private final Logger log = LoggerFactory.getLogger(CreateInvoicePayableService.class);
    private final InvoicePayableCreatedMetricsPort metrics;


    public CreateInvoicePayableService(
        CreateInvoicePayableRepositoryPort repository,
        CreateSupplierRepositoryPort repositorySupplier,
        KafkaTemplate<String,InvoicePayable> kafkaTemplateOrder,
        InvoicePayableCreatedMetricsPort metrics
    ){
        this.repository = repository;
        this.repositorySupplier = repositorySupplier;
        this.kafkaTemplateOrder = kafkaTemplateOrder;
        this.metrics = metrics;  
    }

    @Override
    @Transactional
    @SuppressWarnings("null")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 100 ))
    public InvoicePayable createInvoicePayable(InvoicePayable invoicePayable) {

        Supplier supplier = repositorySupplier.findById(invoicePayable.getSupplier().getId()).orElseThrow(() -> new IllegalStateException("Supplier was not created"));
        log.info("SUPPLIER WAS FINDED: {}", supplier);

        if(invoicePayable.getId() != null) {
            log.warn("INVOICE PAYABLE WAS ALREADY CREATED! invoicePayableId = {}", invoicePayable.getId());
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
        metrics.increment();
        log.info("INVOICE WAS CREATED! {}", invoiceCreated);
        log.info("INVOICE EVENT READY TO SEND TO TOPIC! TOPIC: - Invoice-payable-created-order-processed");
        kafkaTemplateOrder.send("Invoice-payable-created-order-processed", invoiceCreated);
        log.info("EVENT WAS SENDED! {}", invoiceCreated);
        
        return invoiceCreated;
    }

}
