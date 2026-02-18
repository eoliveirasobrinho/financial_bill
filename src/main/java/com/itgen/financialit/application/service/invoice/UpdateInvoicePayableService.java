package com.itgen.financialit.application.service.invoice;


import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.invoice.UpdateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.invoice.UpdateInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.exception.invoice.InvoiceNotFoundException;
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
            throw new InvoiceNotFoundException(invoice.getId());
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
        return updateInvoicePayableRepositoryPort.findById(id);

    }

}
