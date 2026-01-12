package com.itgen.financialit.adapters.out.persistence.mapper;


import java.util.List;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.domain.model.InvoicePayable;
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

    public List<SupplierEntity> toEntityList(List<Supplier> domains) {
        if(domains == null) {
            return List.of();
        }

        return domains.stream().map(this::toEntity).toList();
    }

    public List<Supplier> toDomainList(List<SupplierEntity> entities){
        if(entities == null) {
            return List.of();
        }

        return entities.stream().map(this::toDomain).toList();
    }
}
