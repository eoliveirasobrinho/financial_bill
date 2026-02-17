package com.itgen.financialit.application.port.out.invoice;

import org.springframework.data.jpa.repository.Lock;

import com.itgen.financialit.domain.model.InvoicePayable;

import jakarta.persistence.LockModeType;

public interface UpdateInvoicePayableRepositoryPort {
    InvoicePayable update(InvoicePayable invoicePayable);
    InvoicePayable findById(Long id);
}
