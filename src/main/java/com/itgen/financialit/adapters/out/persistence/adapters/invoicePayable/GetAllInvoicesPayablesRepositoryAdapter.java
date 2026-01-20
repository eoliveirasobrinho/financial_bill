package com.itgen.financialit.adapters.out.persistence.adapters.invoicePayable;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaInvoicePayableRepository;
import com.itgen.financialit.application.port.out.invoice.GetAllInvoicesPayablesRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Repository
public class GetAllInvoicesPayablesRepositoryAdapter implements GetAllInvoicesPayablesRepositoryPort{
    private final JpaInvoicePayableRepository jpaInvoicePayableRepository;
    private final InvoicePayablePersistenceMapper mapper;


    public GetAllInvoicesPayablesRepositoryAdapter(JpaInvoicePayableRepository jpaInvoicePayableRepository, InvoicePayablePersistenceMapper mapper) {
        this.jpaInvoicePayableRepository = jpaInvoicePayableRepository;
        this.mapper = mapper;
    }

    @Override
    public List<InvoicePayable> getAllInvoicesPayables() {
        List<InvoicePayableEntity> invoices = jpaInvoicePayableRepository.findAll();
        List<InvoicePayable> invoicesMapped = mapper.toDomainList(invoices);
        return invoicesMapped;
    }

    
}
