package com.itgen.financialit.adapters.in.rest.mapper;

import org.mapstruct.Mapper;

import com.itgen.financialit.adapters.in.rest.dto.RequestInvoiceReceivedDTO;
import com.itgen.financialit.adapters.in.rest.dto.ResponseInvoiceReceivedDTO;
import com.itgen.financialit.domain.model.InvoiceReceived;


@Mapper(componentModel = "spring")

public interface InvoiceReceivedMapper {

    InvoiceReceived toDomain(RequestInvoiceReceivedDTO invoiceReceivedDTO);
    ResponseInvoiceReceivedDTO toResponseDto(InvoiceReceived dto);

    default InvoiceReceived toInvoiceReceived(RequestInvoiceReceivedDTO dto) {
        return new InvoiceReceived(
            dto.description(),
            dto.amount(),
            dto.dueDate(),
            dto.receivedDate(),
            dto.category()
        );
    }
}
