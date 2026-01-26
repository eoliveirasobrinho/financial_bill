package com.itgen.financialit.adapters.out.metrics.invoice;

import org.springframework.stereotype.Component;

import com.itgen.financialit.application.port.out.metrics.invoice.InvoicePayableMetricsPort;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MicrometerInvoicePayableCreatedMetricsAdapter implements InvoicePayableMetricsPort{
    private final Counter invoicePayableCreated;

    public MicrometerInvoicePayableCreatedMetricsAdapter(MeterRegistry registry) {
        this.invoicePayableCreated = Counter.builder("Invoice_payable_created_total").description("total de contas a serem pagas criadas").register(registry);
    }

    public void increment() {
        invoicePayableCreated.increment();
    }
}
