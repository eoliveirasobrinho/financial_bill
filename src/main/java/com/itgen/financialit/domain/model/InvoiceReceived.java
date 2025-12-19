package com.itgen.financialit.domain.model;

import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class InvoiceReceived {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate dueDate;
    private LocalDate receivedDate;
    private Status status;
    private Category category;

    public InvoiceReceived(String description, BigDecimal amount, LocalDate dueDate, LocalDate receivedDate, Category category) {
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.receivedDate = receivedDate;
        this.category = category;
        this.status = Status.PENDING;
    }

    public InvoiceReceived(Category category, Status status, LocalDate receivedDate, LocalDate dueDate, BigDecimal amount, String description, Long id) {
        this.category = category;
        this.status = status;
        this.receivedDate = receivedDate;
        this.dueDate = dueDate;
        this.amount = amount;
        this.description = description;
        this.id = id;
    }

    public InvoiceReceived(){}


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

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public Status getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }



    private boolean isOverdue() {
        return status != Status.PAID && LocalDate.now().isAfter(dueDate);
    }

    private void pay(LocalDate receivedDate) {
        if(this.status == Status.PAID) {
            throw new IllegalStateException(" Conta já paga");
        }

        this.status = Status.PAID;
        this.receivedDate = receivedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InvoiceReceived that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(dueDate, that.dueDate) && Objects.equals(receivedDate, that.receivedDate) && status == that.status && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, dueDate, receivedDate, status, category);
    }

    @Override
    public String toString() {
        return "InvoiceReceived{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", receivedDate=" + receivedDate +
                ", status=" + status +
                ", category=" + category +
                '}';
    }


}
