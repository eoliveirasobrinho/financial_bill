package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface UpdateInvoicePayableUseCase {
    InvoicePayable updateInvoicePayable(Long id, InvoicePayable invoicePayable);
}
