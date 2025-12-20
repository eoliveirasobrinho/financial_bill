package com.itgen.financialit.adapters.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

public record ResponseInvoiceReceivedDTO(
    Long id,
    String description,
    BigDecimal amount,
    LocalDate dueDate,
    LocalDate receivedDate,
    Status status,
    Category category
) {}
