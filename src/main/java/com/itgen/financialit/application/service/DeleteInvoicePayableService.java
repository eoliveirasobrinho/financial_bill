package com.itgen.financialit.application.service;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.DeleteInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.DeleteInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.exception.invoice.InvoiceNotFoundException;
import com.itgen.financialit.domain.model.InvoicePayable;

import jakarta.transaction.Transactional;

@Service
public class DeleteInvoicePayableService implements DeleteInvoicePayableUseCase{

    private final DeleteInvoicePayableRepositoryPort repository;

    public DeleteInvoicePayableService(DeleteInvoicePayableRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void deleteInvoicePayable(Long id) {
        InvoicePayable invoice = this.findById(id);
        repository.deleteInvoicePayable(invoice.getId());
    }

    @Override
    public InvoicePayable findById(Long id) {
        InvoicePayable invoice = repository.findById(id);

        if(invoice.getId() == null || invoice.getId() != id) {
            throw new InvoiceNotFoundException(invoice.getId());
        }

        return invoice;
    }

}
