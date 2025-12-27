package com.itgen.financialit.application.port.out;

import java.util.List;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface GetAllInvoicesPayablesRepositoryPort {
    List<InvoicePayable> getAllInvoicesPayables();
}
