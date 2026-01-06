package com.itgen.financialit.application.port.out;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface UpdateInvoicePayableRepositoryPort {
    InvoicePayable update(InvoicePayable invoicePayable);
    InvoicePayable findById(Long id);
}
