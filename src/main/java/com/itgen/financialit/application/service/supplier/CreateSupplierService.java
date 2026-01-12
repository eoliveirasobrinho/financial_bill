package com.itgen.financialit.application.service.supplier;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.CreateSupplierUseCase;
import com.itgen.financialit.application.port.out.CreateSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.supplier.SupplierAlreadyExistsException;
import com.itgen.financialit.domain.model.Supplier;

import jakarta.transaction.Transactional;

@Service
public class CreateSupplierService implements CreateSupplierUseCase{

    private final CreateSupplierRepositoryPort repository;

    public CreateSupplierService(CreateSupplierRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        if(supplier.getId() != null) {
            throw new SupplierAlreadyExistsException(supplier.getId());
        }

        return repository.save(supplier);
    }

}
