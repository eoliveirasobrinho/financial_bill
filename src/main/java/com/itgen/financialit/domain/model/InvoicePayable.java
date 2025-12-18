package com.itgen.financialit.domain.model;


import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class InvoicePayable {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private Status status;
    private Category category;
    private Supplier supplier;

    

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

    
    public InvoicePayable(){}
   


    public Long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Status getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }


    public Supplier getSupplier() {
        return supplier;
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
