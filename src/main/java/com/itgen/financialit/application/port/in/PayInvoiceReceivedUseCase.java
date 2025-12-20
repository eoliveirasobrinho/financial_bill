package com.itgen.financialit.application.port.in;

import java.time.LocalDate;

public interface PayInvoiceReceivedUseCase {
    void payInvoiceReceived(LocalDate receivedDate);
}
