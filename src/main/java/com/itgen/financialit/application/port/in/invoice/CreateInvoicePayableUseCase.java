package com.itgen.financialit.application.port.in.invoice;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface CreateInvoicePayableUseCase {
    InvoicePayable createInvoicePayable(InvoicePayable invoicePayable);
}
