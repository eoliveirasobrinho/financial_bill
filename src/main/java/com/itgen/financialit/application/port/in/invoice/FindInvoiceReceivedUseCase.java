package com.itgen.financialit.application.port.in.invoice;

import com.itgen.financialit.domain.model.InvoiceReceived;

public interface FindInvoiceReceivedUseCase {
    InvoiceReceived findInvoiceReceived(Long id);
}
