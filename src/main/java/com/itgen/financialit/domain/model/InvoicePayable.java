package com.itgen.financialit.domain.model;


import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class InvoicePayable {
    private Long id;
    private final String description;
    private final BigDecimal amount;
    private final LocalDate dueDate;
    private LocalDate paymentDate;
    private Status status;
    private final Category category;
    private final Supplier supplier;

    public InvoicePayable(String description, BigDecimal amount, LocalDate dueDate, Category category, Supplier supplier) {
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
        this.supplier = supplier;
    }

    public InvoicePayable(Long id, String description, BigDecimal amount, LocalDate dueDate, Supplier supplier, Category category, LocalDate paymentDate) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.supplier = supplier;
        this.category = category;
        this.paymentDate = paymentDate;
        this.status = Status.PENDING;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }


    public boolean isOverdue() {
        return status != Status.PAID && LocalDate.now().isAfter(dueDate);
    }

    public void pay(LocalDate paymentDate) {
        if(this.status == Status.PAID) {
            throw new IllegalStateException(" Conta já paga");
        }

        this.status = Status.PAID;
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InvoicePayable that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(dueDate, that.dueDate) && Objects.equals(paymentDate, that.paymentDate) && status == that.status && category == that.category && Objects.equals(supplier, that.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, dueDate, paymentDate, status, category, supplier);
    }

    @Override
    public String toString() {
        return "InvoicePayable{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", paymentDate=" + paymentDate +
                ", status=" + status +
                ", category=" + category +
                ", supplierId=" + supplier +
                '}';
    }
}
