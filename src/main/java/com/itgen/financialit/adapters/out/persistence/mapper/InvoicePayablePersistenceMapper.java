package com.itgen.financialit.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;


@Component
public class InvoicePayablePersistenceMapper {

    
    public InvoicePayableEntity toEntity(InvoicePayable domain){
        SupplierEntity supplierEntity = new SupplierEntity(domain.getSupplier().getId());
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

    public InvoicePayable toDomain(InvoicePayableEntity entity){
        Supplier supplier = new Supplier(entity.getSupplierEntity().getId());
        return new InvoicePayable(
            entity.getDescription(),
            entity.getAmount(),
            entity.getDueDate(),
            entity.getCategory(),
            supplier
        );
    }
    
}
