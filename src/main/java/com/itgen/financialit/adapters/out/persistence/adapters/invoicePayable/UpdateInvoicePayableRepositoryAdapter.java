package com.itgen.financialit.adapters.out.persistence.adapters.invoicePayable;


import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaInvoicePayableRepository;
import com.itgen.financialit.application.port.out.invoice.UpdateInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Repository
public class UpdateInvoicePayableRepositoryAdapter implements UpdateInvoicePayableRepositoryPort{
    private final JpaInvoicePayableRepository jpaInvoicePayableRepository;
    private final InvoicePayablePersistenceMapper mapper;


    public UpdateInvoicePayableRepositoryAdapter(JpaInvoicePayableRepository jpaInvoicePayableRepository, InvoicePayablePersistenceMapper mapper) {
        this.jpaInvoicePayableRepository = jpaInvoicePayableRepository;
        this.mapper = mapper;
    }

    @Override
    @CachePut(value = "invoicePayable", key = "#invoicePayable.id")
    public InvoicePayable update(InvoicePayable invoicePayable) {
       InvoicePayableEntity entity = mapper.toEntity(invoicePayable);
       InvoicePayableEntity saved = jpaInvoicePayableRepository.save(entity);
       return mapper.toDomain(saved);

    }

    @Override
    public InvoicePayable findById(Long id) {
        InvoicePayableEntity entity = jpaInvoicePayableRepository.findById(id).orElseThrow(() -> new IllegalStateException("id não encontrado ou nulo"));
        return mapper.toDomain(entity);
    }
    
}
