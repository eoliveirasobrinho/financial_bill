package com.itgen.financialit.adapters.out.persistence.adapters.supplier;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.adapters.out.persistence.mapper.SupplierPersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.repository.JpaSupplierRepository;
import com.itgen.financialit.application.port.out.supplier.GetAllSuppliersRepositoryPort;
import com.itgen.financialit.domain.model.Supplier;

@Repository
public class GetAllSuppliersRepositoryAdapter implements GetAllSuppliersRepositoryPort{

    private final JpaSupplierRepository jpaRepository;
    private final SupplierPersistenceMapper mapper;

    

    public GetAllSuppliersRepositoryAdapter(JpaSupplierRepository jpaRepository, SupplierPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }



    @Override
    public List<Supplier> getAllSuppliers() {
        List<SupplierEntity> suppliersList = jpaRepository.findAll();
        List<Supplier> supplier = mapper.toDomainList(suppliersList);
        return supplier;
    }

}
