package com.itgen.financialit.application.service;

import java.util.NoSuchElementException;

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
    public InvoicePayable findById(Long id) {
        return findByIdInvoicePayableRepositoryPort.findById(id).orElseThrow(() ->  new NoSuchElementException("Invoice was not founded with this ID"));
    }

}
