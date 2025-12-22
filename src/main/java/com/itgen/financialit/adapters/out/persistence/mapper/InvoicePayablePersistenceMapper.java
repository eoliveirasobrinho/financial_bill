package com.itgen.financialit.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;

@Mapper(componentModel = "spring", uses = SupplierPersistenceMapper.class)
public interface InvoicePayablePersistenceMapper {

    @Mapping(target = "supplier", source = "supplier")
    InvoicePayableEntity toEntity(InvoicePayable domain);

    default InvoicePayable toDomain(InvoicePayableEntity entity){
        if(entity == null) {
            return null;
        }

        return new InvoicePayable(
            entity.getDescription(),
            entity.getAmount(),
            entity.getDueDate(),
            entity.getCategory(),
            new Supplier(entity.getSupplier().getId())
        );
    }
    
}
