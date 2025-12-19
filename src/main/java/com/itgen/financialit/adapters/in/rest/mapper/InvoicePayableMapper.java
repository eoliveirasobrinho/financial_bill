package com.itgen.financialit.adapters.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.RequestInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.ResponseInvoicePayableDTO;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;

@Component
@Mapper(componentModel = "spring")
public interface InvoicePayableMapper {
 
    @Mapping(
        target = ".",
        expression = "java(toInvoicePayable(dto))"
    )
    InvoicePayable toDomain(RequestInvoicePayableDTO invoicePayableDTO);

    @Mapping(
        target = "supplierId", source = "supplier.id"
    )
    ResponseInvoicePayableDTO toResponse(InvoicePayable dto);
    
    default InvoicePayable toInvoicePayable(RequestInvoicePayableDTO dto) {
        Supplier supplier = new Supplier(dto.supplierId());

        return new InvoicePayable(
            dto.description(),
            dto.amount(),
            dto.dueDate(),
            dto.category(),
            supplier
        );
    }
    
        
       
    
}   
