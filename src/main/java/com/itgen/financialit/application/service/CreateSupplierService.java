package com.itgen.financialit.application.service;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.CreateSupplierUseCase;
import com.itgen.financialit.application.port.out.CreateSupplierRepositoryPort;
import com.itgen.financialit.domain.model.Supplier;

@Service
public class CreateSupplierService implements CreateSupplierUseCase{

    private final CreateSupplierRepositoryPort repository;

    public CreateSupplierService(CreateSupplierRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        if(supplier.getId() != null) {
            throw new IllegalStateException("Supplier Already Exists ");
        }

        return repository.save(supplier);
    }

}
