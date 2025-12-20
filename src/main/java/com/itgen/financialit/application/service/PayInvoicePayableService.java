package com.itgen.financialit.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.PayInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.PayInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.enums.Status;


@Service
public class PayInvoicePayableService implements PayInvoicePayableUseCase{
    private final PayInvoicePayableRepositoryPort repositoryPort;

    public PayInvoicePayableService(PayInvoicePayableRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }



    @Override
    public void pay(Long id, LocalDate paymentDate) {
        InvoicePayable invoicePayable = repositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("invoice not found"));
       if(invoicePayable.getStatus() == Status.PAID){
            throw new IllegalStateException(" your invoice already be paid");
       }
       
       
       paymentDate = LocalDate.now();
    }
}
