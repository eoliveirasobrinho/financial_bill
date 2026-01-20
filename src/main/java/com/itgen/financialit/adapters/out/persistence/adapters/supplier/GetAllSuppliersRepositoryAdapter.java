package com.itgen.financialit.adapters.out.persistence.adapters.supplier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
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
    private Logger log = LoggerFactory.getLogger(GetAllSuppliersRepositoryAdapter.class);
    

    public GetAllSuppliersRepositoryAdapter(JpaSupplierRepository jpaRepository, SupplierPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }



    @Override
    @Cacheable(value = "supplier")
    public List<Supplier> getAllSuppliers() {
        List<SupplierEntity> suppliersList = jpaRepository.findAll();
        log.info("GETTING SUPPLIERS: {} ", suppliersList);
        List<Supplier> supplier = mapper.toDomainList(suppliersList);
        // Long time = System.currentTimeMillis();
        // log.info("GET ALL SUPPLIERS - TIME PROCESSED:  {}", System.currentTimeMillis());
        return supplier;
    }

}
