package com.itgen.financialit.application.port.in.invoice;

import java.util.List;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface GetAllInvoicesPayableUseCase {
    List<InvoicePayable> getAllInvoicesPayable();
}
