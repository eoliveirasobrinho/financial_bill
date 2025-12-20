package com.itgen.financialit.application.port.out;

import java.util.Optional;



import com.itgen.financialit.domain.model.InvoicePayable;


public interface PayInvoicePayableRepositoryPort {
    Optional<InvoicePayable> findById(Long id);
    void save(InvoicePayable invoicePayable);
}
