package com.itgen.financialit.adapters.out.metrics.invoice;

import org.springframework.stereotype.Component;

import com.itgen.financialit.application.port.out.metrics.invoice.InvoicePayableGetAllInvoicesMetricsPort;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MicrometerInvoiePayableGetAllInvoicesMetricsAdapter implements InvoicePayableGetAllInvoicesMetricsPort{

    private final Counter invoicePayableGetAll;

    public MicrometerInvoiePayableGetAllInvoicesMetricsAdapter(MeterRegistry registry) {
        this.invoicePayableGetAll = Counter.builder("Invoices_payable_called_total").description("total de chamadas que o service fez pra buscar todos os invoices").register(registry);
    }

    @Override
    public void getAll() {
        invoicePayableGetAll.increment();
    }

}
