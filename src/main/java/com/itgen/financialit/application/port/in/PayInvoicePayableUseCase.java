package com.itgen.financialit.application.port.in;

import java.time.LocalDate;

public interface PayInvoicePayableUseCase {
    void pay(Long id, LocalDate paymentDate);
}
