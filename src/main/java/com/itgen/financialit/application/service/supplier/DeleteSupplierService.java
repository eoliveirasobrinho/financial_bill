package com.itgen.financialit.application.service.supplier;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.supplier.DeleteSupplierUseCase;
import com.itgen.financialit.application.port.out.supplier.DeleteSupplierRepositoryPort;
import com.itgen.financialit.domain.exception.supplier.SupplierNotFoundException;
import com.itgen.financialit.domain.model.Supplier;

import jakarta.transaction.Transactional;

@Service
public class DeleteSupplierService implements DeleteSupplierUseCase{

    private final DeleteSupplierRepositoryPort repositoryPort;

    

    public DeleteSupplierService(DeleteSupplierRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        Supplier result = this.findById(id);
        repositoryPort.deleteSupplier(result.getId());
    }

    @Override
    public Supplier findById(Long id) {
        Supplier result = repositoryPort.findById(id);
        if(result == null) {
            throw new SupplierNotFoundException(id);
        }
        return result;
    }

}
