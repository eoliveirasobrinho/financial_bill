package com.itgen.financialit.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;

@Component
@Mapper(componentModel = "spring")
public interface InvoicePayablePersistence {

    @Mapping(
        target = ".",
        conditionExpression = "java(toInvoicePayablePersistence(entity))"
    )
    InvoicePayable toDomain(InvoicePayableEntity entity);

    @Mapping(target = "supplierId", source = "supplier.id")
    InvoicePayableEntity toEntity(InvoicePayable domain);

    default InvoicePayable toInvoicePayablePersistence(InvoicePayableEntity entity) {
        Supplier supplier = new Supplier(entity.getId());
        return new InvoicePayable(
            entity.getDescription(),
            entity.getAmount(),
            entity.getDueDate(),
            entity.getCategory(),
            supplier
        );
    }
}
