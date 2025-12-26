package com.itgen.financialit.adapters.in.rest.mapper;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.RequestSuplierDTO;
import com.itgen.financialit.adapters.in.rest.dto.ResponseSupplierDTO;
import com.itgen.financialit.domain.model.Supplier;

@Component
public class SupplierMapper {
    public Supplier toDomain(RequestSuplierDTO dto) {
        
        return new Supplier(
            dto.name(),
            dto.document(),
            dto.email()
        );
    }

    public ResponseSupplierDTO toResponse(Supplier domain) {
        return new ResponseSupplierDTO(
            domain.getId(),
            domain.getName(),
            domain.getDocument(),
            domain.getEmail()
        );
    }
}
