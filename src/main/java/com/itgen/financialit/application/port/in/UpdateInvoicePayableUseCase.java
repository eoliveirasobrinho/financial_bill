package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface UpdateInvoicePayableUseCase {
    InvoicePayable updateInvoicePayable(InvoicePayable invoicePayable);
    InvoicePayable findById(Long id);
}
