package com.itgen.financialit.application.service.supplier;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.supplier.UpdateSupplierUseCase;
import com.itgen.financialit.application.port.out.supplier.UpdateSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.supplier.SupplierNotFoundException;
import com.itgen.financialit.domain.model.Supplier;

import jakarta.transaction.Transactional;

@Service
public class UpdateSupplierService implements UpdateSupplierUseCase{

    private final UpdateSupplierRepositoryPort repositoryPort;

    

    public UpdateSupplierService(UpdateSupplierRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public Supplier updateSupplier(Supplier supplier) {
        Supplier result = this.findById(supplier.getId());
        if(result == null) {
            throw new SupplierNotFoundException(supplier.getId());
        }

        result.setName(supplier.getName());
        result.setDocument(supplier.getDocument());
        result.setEmail(supplier.getEmail());

        return repositoryPort.updateSupplier(result);
    }

    @Override
    public Supplier findById(Long id) {
        return repositoryPort.findById(id);
    }

}
