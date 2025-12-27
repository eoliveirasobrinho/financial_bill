package com.itgen.financialit.adapters.out.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;


@Component
public class InvoicePayablePersistenceMapper {

    
    public InvoicePayableEntity toEntity(InvoicePayable domain){

        SupplierEntity supplierEntity = new SupplierEntity(domain.getSupplier().getId(),
        domain.getSupplier().getName(),
        domain.getSupplier().getDocument(),
        domain.getSupplier().getEmail());

        return new InvoicePayableEntity(
            domain.getId(),
            domain.getDescription(),
            domain.getAmount(),
            domain.getDueDate(),
            domain.getPaymentDate(),
            domain.getStatus(),
            domain.getCategory(),
            supplierEntity
        );
    }

    public List<InvoicePayableEntity> toEntityList(List<InvoicePayable> domains) {
        if(domains == null) {
            return List.of();
        }

        return domains.stream().map(this::toEntity).toList();
    }

    public InvoicePayable toDomain(InvoicePayableEntity entity){
        
        Supplier supplier = new Supplier(entity.getSupplierEntity().getId(),
        entity.getSupplierEntity().getName(),
        entity.getSupplierEntity().getDocument(),
        entity.getSupplierEntity().getEmail());

        return new InvoicePayable(
            entity.getDescription(),
            entity.getAmount(),
            entity.getDueDate(),
            entity.getCategory(),
            supplier
        );
    }

    public List<InvoicePayable> toDomainList(List<InvoicePayableEntity> entities){
        if(entities == null) {
            return List.of();
        }

        return entities.stream().map(this::toDomain).toList();
    }
    
}
