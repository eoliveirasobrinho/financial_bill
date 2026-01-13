package com.itgen.financialit.application.port.in.invoice;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface DeleteInvoicePayableUseCase {
    void deleteInvoicePayable(Long id);
    InvoicePayable findById(Long id);
}
