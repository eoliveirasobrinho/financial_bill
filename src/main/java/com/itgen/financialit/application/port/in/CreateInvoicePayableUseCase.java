package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface CreateInvoicePayableUseCase {
    InvoicePayable creatInvoicePayable(InvoicePayable invoicePayable);
}
