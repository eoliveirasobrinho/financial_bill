package com.itgen.financialit.application.port.out.invoice;


import com.itgen.financialit.domain.model.InvoicePayable;


public interface CreateInvoicePayableRepositoryPort {
    InvoicePayable save(InvoicePayable invoicePayable);
}
