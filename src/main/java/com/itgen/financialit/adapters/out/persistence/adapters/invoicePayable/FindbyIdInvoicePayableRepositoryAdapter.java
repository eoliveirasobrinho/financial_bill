package com.itgen.financialit.adapters.out.persistence.adapters.invoicePayable;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaInvoicePayableRepository;
import com.itgen.financialit.application.port.out.invoice.FindByIdInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Repository
public class FindbyIdInvoicePayableRepositoryAdapter implements FindByIdInvoicePayableRepositoryPort{

    private final JpaInvoicePayableRepository jpaInvoicePayableRepository;
    private final InvoicePayablePersistenceMapper mapper;

    public FindbyIdInvoicePayableRepositoryAdapter(JpaInvoicePayableRepository jpaInvoicePayableRepository, InvoicePayablePersistenceMapper mapper) {
        this.jpaInvoicePayableRepository = jpaInvoicePayableRepository;
        this.mapper = mapper;
    }
    @Override
    public Optional<InvoicePayable> findById(Long id) {
        Optional<InvoicePayableEntity> invoiceEntity = jpaInvoicePayableRepository.findById(Objects.requireNonNull(id));
        if(invoiceEntity.isEmpty()) {
            throw new NoSuchElementException("No value for this id " +  invoiceEntity.get().getId());
        }
        InvoicePayableEntity invoiceToMap = invoiceEntity.get();
        InvoicePayable invoiceToOptional  = mapper.toDomain(invoiceToMap);
        Optional<InvoicePayable> invoice = Optional.ofNullable(invoiceToOptional);
        return invoice;
    }

}
