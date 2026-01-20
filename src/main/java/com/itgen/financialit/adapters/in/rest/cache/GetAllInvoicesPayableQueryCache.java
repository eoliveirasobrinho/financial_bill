package com.itgen.financialit.adapters.in.rest.cache;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.response.ResponseInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.mapper.InvoicePayableMapper;
import com.itgen.financialit.application.service.invoice.GetAllInvoicesPayablesService;
import com.itgen.financialit.domain.model.InvoicePayable;

@Component
public class GetAllInvoicesPayableQueryCache {
    private final GetAllInvoicesPayablesService service;
    private final InvoicePayableMapper mapper;

    public GetAllInvoicesPayableQueryCache(GetAllInvoicesPayablesService service, InvoicePayableMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Cacheable(value = "invoicePayable:list")
    public List<ResponseInvoicePayableDTO> getAllInvoicesPayableCached() {
        List<InvoicePayable> invoices = service.getAllInvoicesPayable();
        return mapper.toResponseList(invoices); 
    }
    
}
