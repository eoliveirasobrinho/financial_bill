package com.itgen.financialit.application.service.supplier;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.supplier.GetSupplierByIdUseCase;
import com.itgen.financialit.application.port.out.supplier.GetSupplierByIdRepositoryPort;
import com.itgen.financialit.domain.exception.supplier.SupplierNotFoundException;
import com.itgen.financialit.domain.model.Supplier;

@Service
public class GetSupplierByIdService implements GetSupplierByIdUseCase{

    private final GetSupplierByIdRepositoryPort repositoryPort;

    

    public GetSupplierByIdService(GetSupplierByIdRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
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
