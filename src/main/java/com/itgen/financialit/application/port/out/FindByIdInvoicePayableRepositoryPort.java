package com.itgen.financialit.application.port.out;

import java.util.Optional;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface FindByIdInvoicePayableRepositoryPort {
    Optional<InvoicePayable> findById(Long Id);
}
