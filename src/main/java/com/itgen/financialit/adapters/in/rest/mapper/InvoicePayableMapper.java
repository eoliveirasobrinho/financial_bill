package com.itgen.financialit.adapters.in.rest.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.RequestInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.ResponseInvoicePayableDTO;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;

@Component
public class InvoicePayableMapper {
 

    public InvoicePayable toDomain(RequestInvoicePayableDTO dto) {
        Supplier supplier = new Supplier(dto.supplierId());
        return new InvoicePayable(
            dto.description(),
            dto.amount(),
            dto.dueDate(),
            dto.category(),
            supplier
        );
    }
    
    public ResponseInvoicePayableDTO toResponse(InvoicePayable domain) {
        return new ResponseInvoicePayableDTO(
            domain.getId(),
            domain.getDescription(),
            domain.getAmount(),
            domain.getDueDate(),
            domain.getPaymentDate(),
            domain.getStatus(),
            domain.getCategory(),
            domain.getSupplier().getId()
        );
    }
       
    
}   
