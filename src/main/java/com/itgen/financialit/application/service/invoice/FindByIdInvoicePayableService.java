package com.itgen.financialit.application.service.invoice;


import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.invoice.FindByIdInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.invoice.FindByIdInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.exception.invoice.InvoiceNotFoundException;
import com.itgen.financialit.domain.model.InvoicePayable;

@Service
public class FindByIdInvoicePayableService implements FindByIdInvoicePayableUseCase{

    private final FindByIdInvoicePayableRepositoryPort findByIdInvoicePayableRepositoryPort;

    public FindByIdInvoicePayableService(FindByIdInvoicePayableRepositoryPort findByIdInvoicePayableRepositoryPort){
        this.findByIdInvoicePayableRepositoryPort = findByIdInvoicePayableRepositoryPort;
    }

    @Override
    public InvoicePayable findById(Long id) {
        return findByIdInvoicePayableRepositoryPort.findById(id).orElseThrow(() ->  new InvoiceNotFoundException(id));
    }

}
