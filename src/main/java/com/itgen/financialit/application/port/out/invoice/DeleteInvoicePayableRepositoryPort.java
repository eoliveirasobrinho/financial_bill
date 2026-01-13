package com.itgen.financialit.application.port.out.invoice;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface DeleteInvoicePayableRepositoryPort {
    void deleteInvoicePayable(Long id);
    InvoicePayable findById(Long id);
}
