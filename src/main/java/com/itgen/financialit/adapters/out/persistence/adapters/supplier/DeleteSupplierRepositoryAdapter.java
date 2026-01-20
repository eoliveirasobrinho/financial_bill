package com.itgen.financialit.adapters.out.persistence.adapters.supplier;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.SupplierPersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaSupplierRepository;
import com.itgen.financialit.application.port.out.supplier.DeleteSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.supplier.SupplierNotFoundException;
import com.itgen.financialit.domain.model.Supplier;

@Repository
public class DeleteSupplierRepositoryAdapter implements DeleteSupplierRepositoryPort {

    private final JpaSupplierRepository repository;
    private final SupplierPersistenceMapper mapper;

    

    public DeleteSupplierRepositoryAdapter(JpaSupplierRepository repository, SupplierPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(value = "supplier", allEntries = true)
    public void deleteSupplier(Long id) {
        Supplier result = this.findById(id);
        SupplierEntity supplier = mapper.toEntity(result);
        repository.delete(supplier);

    }

    @Override
    @Cacheable(value = "supplier", key = "#supplier")
    public Supplier findById(Long id) {
       SupplierEntity result = repository.findById(id).orElseThrow( () -> new SupplierNotFoundException(id));
       return mapper.toDomain(result);
    }

}
