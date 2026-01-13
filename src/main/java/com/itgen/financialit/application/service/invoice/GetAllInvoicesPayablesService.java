package com.itgen.financialit.application.service.invoice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.invoice.GetAllInvoicesPayableUseCase;
import com.itgen.financialit.application.port.out.invoice.GetAllInvoicesPayablesRepositoryPort;
import com.itgen.financialit.domain.model.InvoicePayable;

@Service
public class GetAllInvoicesPayablesService implements GetAllInvoicesPayableUseCase{

    private GetAllInvoicesPayablesRepositoryPort repositoryPort;

    public GetAllInvoicesPayablesService(GetAllInvoicesPayablesRepositoryPort repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<InvoicePayable> getAllInvoicesPayable() {
        List<InvoicePayable> invoices = repositoryPort.getAllInvoicesPayables();
        if(invoices.isEmpty()){
            return List.of();
        }

        return invoices;
    }

}
