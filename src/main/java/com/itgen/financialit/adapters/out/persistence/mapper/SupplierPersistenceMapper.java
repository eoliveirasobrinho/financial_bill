package com.itgen.financialit.adapters.out.persistence.mapper;


import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.domain.model.Supplier;

@Component
public class SupplierPersistenceMapper {
    public SupplierEntity toEntity(Supplier domain) {
        
        return new SupplierEntity(
            domain.getId(),
            domain.getName(),
            domain.getDocument(),
            domain.getEmail()
        );
    }
    public Supplier toDomain(SupplierEntity entity) {
        return new Supplier(
            entity.getId(),
            entity.getName(),
            entity.getDocument(),
            entity.getEmail()
        );
    }
}
