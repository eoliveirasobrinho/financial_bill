package com.itgen.financialit.adapters.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale.Category;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;

public record RequestInvoicePayableDTO(
    String description, 
    BigDecimal amount,
    LocalDate dueDate,
    Category category, 
    SupplierEntity supplier) {
}
