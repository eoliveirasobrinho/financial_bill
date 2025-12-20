package com.itgen.financialit.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;



import com.itgen.financialit.adapters.out.persistence.entity.InvoiceReceivedEntity;
import com.itgen.financialit.domain.model.InvoiceReceived;

@Mapper(componentModel = "spring")
public interface InvoiceReceivedPersistenceMapper {
    
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
