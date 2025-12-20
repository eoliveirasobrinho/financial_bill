package com.itgen.financialit.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.domain.model.InvoicePayable;
import com.itgen.financialit.domain.model.Supplier;

@Mapper(componentModel = "spring")
public interface InvoicePayablePersistenceMapper {

    InvoicePayable toDomain(InvoicePayableEntity entity);

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
