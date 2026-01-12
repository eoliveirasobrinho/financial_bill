package com.itgen.financialit.adapters.in.rest.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.request.RequestSuplierDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseSupplierDTO;
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

    public List<ResponseSupplierDTO> toResponseList(List<Supplier> list) {
        if(list == null) {
            return List.of();
        }

        return list.stream().map(this::toResponse).toList();
    }

    
}
