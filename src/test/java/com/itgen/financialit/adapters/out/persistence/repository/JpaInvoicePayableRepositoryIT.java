package com.itgen.financialit.adapters.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import com.itgen.financialit.adapters.out.persistence.entity.InvoicePayableEntity;
import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.domain.model.enums.Category;
import com.itgen.financialit.domain.model.enums.Status;

@DataJpaTest
@EntityScan("com.itgen.financialit.adapters.out.persistence.entity")
@EnableJpaRepositories("com.itgen.financialit.adapters.out.persistence.repository")
@ActiveProfiles("test")
class JpaInvoicePayableRepositoryIT {

    @Autowired
    private JpaInvoicePayableRepository invoiceRepository;

    @Autowired
    private JpaSupplierRepository supplierRepository;

    @Test
    void shouldSaveAndFindInvoicePayableWithSupplier() {
        // arrange - primeiro o Supplier
        SupplierEntity supplier = new SupplierEntity();
        supplier.setName("Fornecedor Teste");
        supplier.setDocument("78993824");
        supplier.setEmail("testeIntegração@gmail.com");

        SupplierEntity savedSupplier = supplierRepository.save(supplier);

        // agora a Invoice
        InvoicePayableEntity invoice = new InvoicePayableEntity();
        invoice.setDescription("Conta de Internet");
        invoice.setAmount(new BigDecimal("199.90"));
        invoice.setDueDate(LocalDate.now().plusDays(10));
        invoice.setCategory(Category.OTHER);
        invoice.setSupplierEntity(savedSupplier);
        invoice.setStatus(Status.PENDING);

        // act
        InvoicePayableEntity savedInvoice = invoiceRepository.save(invoice);

        // assert
        assertThat(savedInvoice.getId()).isNotNull();
        assertThat(savedInvoice.getSupplierEntity().getId())
            .isEqualTo(savedSupplier.getId());
    }
}
