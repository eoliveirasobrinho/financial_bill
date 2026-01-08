package com.itgen.financialit.adapters.in.rest.mapper;

import java.util.List;


import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.request.RequestInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseSupplierDTO;
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
            toSupplierResponse(domain.getSupplier())
        );
    }

    public List<ResponseInvoicePayableDTO> toResponseList(List<InvoicePayable> list) {
        if(list == null) {
            return List.of();
        }

        return list.stream().map(this::toResponse).toList();
    }

     private ResponseSupplierDTO toSupplierResponse(Supplier supplier) {
        if (supplier == null) return null;

        return new ResponseSupplierDTO(
            supplier.getId(),
            supplier.getName(),
            supplier.getDocument(),
            supplier.getEmail());
    }
       
    
}   
