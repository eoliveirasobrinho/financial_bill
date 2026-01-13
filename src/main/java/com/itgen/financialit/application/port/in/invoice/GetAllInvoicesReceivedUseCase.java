package com.itgen.financialit.application.port.in.invoice;

import com.itgen.financialit.domain.model.InvoiceReceived;
import java.util.List;

public interface GetAllInvoicesReceivedUseCase {
    List<InvoiceReceived> getAllReceivedInvoices();
}
