package com.itgen.financialit.application.port.out;


import java.util.Optional;

import com.itgen.financialit.domain.model.InvoicePayable;


public interface CreateInvoicePayableRepositoryPort {
    Optional<InvoicePayable> findById(Long id);
    InvoicePayable save(InvoicePayable invoicePayable);
}
