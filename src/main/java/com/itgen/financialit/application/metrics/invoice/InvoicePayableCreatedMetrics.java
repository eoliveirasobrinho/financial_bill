package com.itgen.financialit.application.metrics.invoice;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class InvoicePayableCreatedMetrics {
    private final Counter invoicePayableCreated;

    public InvoicePayableCreatedMetrics(MeterRegistry registry) {
        this.invoicePayableCreated = Counter.builder("Invoice_payable_created_total").description("total de contas a serem pagas criadas").register(registry);
    }

    public void increment() {
        invoicePayableCreated.increment();
    }
}
