package com.itgen.financialit.adapters.out.persistence;

import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaInvoicePayableRepository;
import com.itgen.financialit.application.port.out.invoice.DeleteInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.exception.invoice.InvoiceNotFoundException;
import com.itgen.financialit.domain.model.InvoicePayable;

@Repository
public class DeleteInvoicePayableRepositoryAdapter implements DeleteInvoicePayableRepositoryPort{

    private final JpaInvoicePayableRepository jpaRepository;
    private final InvoicePayablePersistenceMapper mapper;

    public DeleteInvoicePayableRepositoryAdapter(JpaInvoicePayableRepository jpaRepository, InvoicePayablePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void deleteInvoicePayable(Long id) {
        InvoicePayable invoice = this.findById(id);
        InvoicePayableEntity deleteInvoice = mapper.toEntity(invoice);
        jpaRepository.delete(deleteInvoice);
    }

    @Override
    public InvoicePayable findById(Long id) {
        InvoicePayableEntity invoice = jpaRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));
        return mapper.toDomain(invoice);
    }

}
