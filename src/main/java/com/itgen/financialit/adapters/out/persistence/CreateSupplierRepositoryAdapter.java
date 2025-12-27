package com.itgen.financialit.adapters.out.persistence;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.SupplierPersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaSupplierRepository;
import com.itgen.financialit.application.port.out.CreateSupplierRepositoryPort;
import com.itgen.financialit.domain.model.Supplier;

import jakarta.transaction.Transactional;

@Repository
public class CreateSupplierRepositoryAdapter implements CreateSupplierRepositoryPort{

    private final JpaSupplierRepository repository;
    private final SupplierPersistenceMapper mapper;

    public CreateSupplierRepositoryAdapter(JpaSupplierRepository repository, SupplierPersistenceMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    @Override
    public Supplier save(Supplier supplier) {
        SupplierEntity supplierEntity = mapper.toEntity(supplier);
        SupplierEntity supplierCreated = repository.save(Objects.requireNonNull(supplierEntity));
        Supplier supplierSaved = mapper.toDomain(supplierCreated); 
        return supplierSaved;
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return repository.findById(Objects.requireNonNull(id)).map(mapper::toDomain);
    }

}
