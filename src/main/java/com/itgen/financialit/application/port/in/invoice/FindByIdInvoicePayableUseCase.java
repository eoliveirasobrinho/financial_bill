package com.itgen.financialit.application.port.in.invoice;


import com.itgen.financialit.domain.model.InvoicePayable;

public interface FindByIdInvoicePayableUseCase {
    InvoicePayable findById(Long Id);
}
