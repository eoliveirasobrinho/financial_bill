package com.itgen.financialit.adapters.in.rest.mapper;

import org.springframework.stereotype.Component;

import com.itgen.financialit.adapters.in.rest.dto.request.RequestInvoiceReceivedDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseInvoiceReceivedDTO;
import com.itgen.financialit.domain.model.InvoiceReceived;


@Component
public class InvoiceReceivedMapper {

    public InvoiceReceived toDomain(RequestInvoiceReceivedDTO invoiceReceivedDTO) {
        return new InvoiceReceived(
            invoiceReceivedDTO.description(),
            invoiceReceivedDTO.amount(),
            invoiceReceivedDTO.dueDate(),
            invoiceReceivedDTO.receivedDate(),
            invoiceReceivedDTO.category()
        );
    }
    public ResponseInvoiceReceivedDTO toResponseDto(InvoiceReceived dto) {
        return new ResponseInvoiceReceivedDTO(
            dto.getId(),
            dto.getDescription(),
            dto.getAmount(),
            dto.getDueDate(),
            dto.getReceivedDate(),
            dto.getStatus(),
            dto.getCategory()
        );
    }

    
}
