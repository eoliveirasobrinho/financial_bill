package com.itgen.financialit.adapters.in.rest.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

public record ResponseInvoicePayableDTO(
    Long id, 
    String description, 
    BigDecimal amount, 
    LocalDate dueDate, 
    LocalDate paymentDate, 
    Status status, 
    Category category, 
    ResponseSupplierDTO supplier) {
}
