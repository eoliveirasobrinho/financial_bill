package com.itgen.financialit.application.service;


import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.UpdateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.UpdateInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

import jakarta.transaction.Transactional;

@Service
public class UpdateInvoicePayableService implements UpdateInvoicePayableUseCase{

    private final UpdateInvoicePayableRepositoryPort updateInvoicePayableRepositoryPort;

    public UpdateInvoicePayableService(UpdateInvoicePayableRepositoryPort updateInvoicePayableRepositoryPort){
        this.updateInvoicePayableRepositoryPort = updateInvoicePayableRepositoryPort;
    }

    @Override
    @Transactional
    public InvoicePayable updateInvoicePayable(InvoicePayable invoicePayable) {
        InvoicePayable invoice = this.findById(invoicePayable.getId());
        if(invoice == null) {
            throw new IllegalStateException("id não encontrada");
        }

        invoice.setCategory(invoicePayable.getCategory());
        invoice.setAmount(invoicePayable.getAmount());
        invoice.setDescription(invoicePayable.getDescription());
        invoice.setDueDate(invoicePayable.getDueDate());
        invoice.setPaymentDate(invoicePayable.getPaymentDate());
        invoice.setStatus(invoicePayable.getStatus());
        invoice.setSupplier(invoicePayable.getSupplier());
    
        return updateInvoicePayableRepositoryPort.update(invoice);
    }

    @Override
    public InvoicePayable findById(Long id) {
        InvoicePayable invoice = updateInvoicePayableRepositoryPort.findById(id);
        return invoice;
    }

}
