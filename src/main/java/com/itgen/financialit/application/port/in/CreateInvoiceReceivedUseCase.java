package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.InvoiceReceived;

public interface CreateInvoiceReceivedUseCase {
    InvoiceReceived creatInvoiceReceived(InvoiceReceived invoiceReceived);
}
