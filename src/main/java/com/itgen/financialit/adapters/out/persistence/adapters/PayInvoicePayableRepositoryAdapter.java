package com.itgen.financialit.adapters.out.persistence.adapters;

import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaInvoicePayableRepository;
import com.itgen.financialit.application.port.out.PayInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Repository
public class PayInvoicePayableRepositoryAdapter implements PayInvoicePayableRepositoryPort{

    private final JpaInvoicePayableRepository jpaInvoicePayableRepository;  
    private final InvoicePayablePersistenceMapper mapper;

    public PayInvoicePayableRepositoryAdapter(
        JpaInvoicePayableRepository jpaInvoicePayableRepository,
        InvoicePayablePersistenceMapper mapper
    ) {
        this.mapper = mapper;
        this.jpaInvoicePayableRepository = jpaInvoicePayableRepository;
    }

    @Override
    public Optional<InvoicePayable> findById(Long id) {
       return jpaInvoicePayableRepository.findById(Objects.requireNonNull(id)).map(mapper::toDomain);
    }

    @Override
    public void save(InvoicePayable invoicePayable) {
        InvoicePayableEntity invoicePayableEntity = mapper.toEntity(invoicePayable);
        jpaInvoicePayableRepository.save(Objects.requireNonNull(invoicePayableEntity));
    }

    

}
