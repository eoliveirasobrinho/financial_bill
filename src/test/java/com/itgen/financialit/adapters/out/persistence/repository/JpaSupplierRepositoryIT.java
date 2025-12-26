package com.itgen.financialit.adapters.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;

@DataJpaTest
@EntityScan("com.itgen.financialit.adapters.out.persistence.entity")
@EnableJpaRepositories("com.itgen.financialit.adapters.out.persistence.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class JpaSupplierRepositoryIT {

    @Autowired
    private JpaSupplierRepository repository;

    @Test
    void shouldSaveAndFindSupplier() {
        // arrange
        SupplierEntity supplier = new SupplierEntity();
        supplier.setName("Fornecedor Integração");
        supplier.setDocument("1237123281");
        supplier.setEmail("testeIntegração@gmail.com");
        supplier.setId(1L);

        // act
        SupplierEntity saved = repository.save(supplier);

        // assert
        assertThat(saved.getId()).isNotNull();

        assertThat(repository.findById(saved.getId()))
            .isPresent()
            .get()
            .extracting(SupplierEntity::getName)
            .isEqualTo("Fornecedor Integração");
    }

    @Test
    void shouldReturnEmptyWhenSupplierNotFound() {
        // act
        var result = repository.findById(999L);

        // assert
        assertThat(result).isEmpty();
    }
}
