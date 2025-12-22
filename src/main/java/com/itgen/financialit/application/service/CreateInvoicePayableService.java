package com.itgen.financialit.application.service;



import org.springframework.stereotype.Service;
import com.itgen.financialit.application.port.in.CreateInvoicePayableUseCase;
import com.itgen.financialit.application.port.out.CreateInvoicePayableRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Service
public class CreateInvoicePayableService implements CreateInvoicePayableUseCase{


    private final CreateInvoicePayableRepositoryPort repository;

    public CreateInvoicePayableService(
        CreateInvoicePayableRepositoryPort repository
    ){
        this.repository = repository;     
    }

    @Override
    public InvoicePayable createInvoicePayable(InvoicePayable invoicePayable) {

        if(invoicePayable.getId() != null) {
            throw new IllegalStateException("Invoice already has an ID and cannot be created");
        }
        
        return repository.save(invoicePayable);
    }

}
