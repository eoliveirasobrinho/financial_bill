package com.itgen.financialit.application.port.in;

import java.util.Optional;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface FindByIdInvoicePayableUseCase {
    Optional<InvoicePayable> findById(Long Id);
}
