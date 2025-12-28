package com.itgen.financialit.adapters.out.persistence.entity;

import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoice_payable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePayableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate dueDate;
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplierEntity;

    public InvoicePayableEntity(Long id , String description, BigDecimal amount, LocalDate dueDate, Category category, SupplierEntity supplierEntity) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paymentDate = null;
        this.category = category;
        this.supplierEntity = supplierEntity;
        this.status = Status.PENDING;
    }
}
