package com.itgen.financialit.adapters.out.persistence;

import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.SupplierPersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaSupplierRepository;
import com.itgen.financialit.application.port.out.UpdateSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.supplier.SupplierNotFoundException;
import com.itgen.financialit.domain.model.Supplier;

@Repository
public class UpdateSupplierRepositoryAdapter implements UpdateSupplierRepositoryPort{

    private final JpaSupplierRepository repository;
    private final SupplierPersistenceMapper mapper;

    

    public UpdateSupplierRepositoryAdapter(JpaSupplierRepository repository, SupplierPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        SupplierEntity result = mapper.toEntity(supplier);
        SupplierEntity saved = repository.save(result);
        return mapper.toDomain(saved);
    }

    @Override
    public Supplier findById(long id) {
        SupplierEntity result = repository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
        return mapper.toDomain(result);
    }

}
