package com.itgen.financialit.application.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.FindByIdInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.FindByIdInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Service
public class FindByIdInvoicePayableService implements FindByIdInvoicePayableUseCase{

    private final FindByIdInvoicePayableRepositoryPort findByIdInvoicePayableRepositoryPort;

    public FindByIdInvoicePayableService(FindByIdInvoicePayableRepositoryPort findByIdInvoicePayableRepositoryPort){
        this.findByIdInvoicePayableRepositoryPort = findByIdInvoicePayableRepositoryPort;
    }

    @Override
    public Optional<InvoicePayable> findById(Long id) {
        Optional<InvoicePayable> invoice = findByIdInvoicePayableRepositoryPort.findById(id);
        if(invoice.get().getId() == null) {
            throw new NoSuchElementException("there's no invoice created");
        }

        return invoice;
    }

}
