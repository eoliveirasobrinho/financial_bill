package com.itgen.financialit.adapters.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itgen.financialit.domain.model.enums.Category;


public record RequestInvoicePayableDTO(
    String description, 
    BigDecimal amount,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dueDate,
    Category category, 
    Long supplierId) {
}
