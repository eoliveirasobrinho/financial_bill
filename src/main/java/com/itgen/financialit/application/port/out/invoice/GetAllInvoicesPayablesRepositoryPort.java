package com.itgen.financialit.application.port.out.invoice;

import java.util.List;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface GetAllInvoicesPayablesRepositoryPort {
    List<InvoicePayable> getAllInvoicesPayables();
}
