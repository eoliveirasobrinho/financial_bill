package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface FindInvoicePayableUseCase {
    InvoicePayable findInvoicePayableUseCase(Long id);
}
