package com.itgen.financialit.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.out.persistence.entity.InvoiceReceivedEntity;
import com.itgen.financialit.domain.model.InvoiceReceived;

@Mapper(componentModel = "spring")
@Component
public interface InvoiceReceivedPersistence {
    @Mapping(
        target = ".",
        conditionExpression = "java(toInvoiceReceivedEntity(entity))"
    )
    InvoiceReceived toDomain(InvoiceReceivedEntity entity);

    InvoiceReceivedEntity toEntity(InvoiceReceived domain);

    default InvoiceReceived toInvoiceReceivedEntity(InvoiceReceivedEntity entity) {
        return new InvoiceReceived(
            entity.getDescription(),
            entity.getAmount(),
            entity.getDueDate(),
            entity.getReceivedDate(),
            entity.getCategory()
        );
    }
}
