package com.itgen.financialit.adapters.out.persistence.adapters.invoicePayable;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;

import com.itgen.financialit.adapters.out.persistence.repository.JpaInvoicePayableRepository;
import com.itgen.financialit.application.port.out.invoice.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Repository
public class CreateInvoicePayableRepositoryAdapter implements CreateInvoicePayableRepositoryPort{

    private final JpaInvoicePayableRepository jpaRepository;
    private final InvoicePayablePersistenceMapper mapper; 
    private final Logger log = LoggerFactory.getLogger(CreateInvoicePayableRepositoryAdapter.class);
    

    public CreateInvoicePayableRepositoryAdapter(
        JpaInvoicePayableRepository jpaResosRepository,
        InvoicePayablePersistenceMapper mapper
        
    ){
        this.jpaRepository = jpaResosRepository;
        this.mapper = mapper;
        
    }


    @Override
    public InvoicePayable save(InvoicePayable invoicePayable) {
      log.debug("INVOICE WAS RECEIVED FROM SERVICE: {}", invoicePayable);
      InvoicePayableEntity invoicePayableEntity = mapper.toEntity(invoicePayable);
      InvoicePayableEntity invoiceCreated = jpaRepository.save(Objects.requireNonNull(invoicePayableEntity));
      log.info("INVOICE WAS SAVED! {} - invoiceID={}", invoiceCreated, invoiceCreated.getId());
      InvoicePayable invoiceMapped = mapper.toDomain(invoiceCreated);
        return invoiceMapped;
    }


}
