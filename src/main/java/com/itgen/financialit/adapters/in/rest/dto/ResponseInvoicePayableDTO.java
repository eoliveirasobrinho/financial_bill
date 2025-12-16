package com.itgen.financialit.adapters.in.rest.dto;

import java.io.ObjectInputFilter.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale.Category;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;

public record ResponseInvoicePayableDTO(
    Long id, 
    String description, 
    BigDecimal amount, 
    LocalDate dueDate, 
    LocalDate paymenDate, 
    Status status, 
    Category category, 
    SupplierEntity supplier) {

}
