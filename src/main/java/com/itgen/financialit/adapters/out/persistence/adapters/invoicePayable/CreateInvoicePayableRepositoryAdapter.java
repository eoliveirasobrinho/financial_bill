package com.itgen.financialit.adapters.out.persistence.adapters.invoicePayable;

import java.util.Objects;

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
    

    public CreateInvoicePayableRepositoryAdapter(
        JpaInvoicePayableRepository jpaResosRepository,
        InvoicePayablePersistenceMapper mapper
        
    ){
        this.jpaRepository = jpaResosRepository;
        this.mapper = mapper;
        
    }


    @Override
    public InvoicePayable save(InvoicePayable invoicePayable) {
      InvoicePayableEntity invoicePayableEntity = mapper.toEntity(invoicePayable);
      InvoicePayableEntity invoiceCreated = jpaRepository.save(Objects.requireNonNull(invoicePayableEntity));
      InvoicePayable invoiceMapped = mapper.toDomain(invoiceCreated);
        return invoiceMapped;
    }


}
