package com.itgen.financialit.application.service.supplier;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itgen.financialit.application.port.in.GetAllSuppliersUseCase;
import com.itgen.financialit.application.port.out.GetAllSuppliersRepositoryPort;
import com.itgen.financialit.domain.model.Supplier;

@Service
public class GetAllSuppliersService implements GetAllSuppliersUseCase{

    private final GetAllSuppliersRepositoryPort repository;
    
    

    public GetAllSuppliersService(GetAllSuppliersRepositoryPort repository) {
        this.repository = repository;
    }



    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = repository.getAllSuppliers();

        if(suppliers.isEmpty()){
            return List.of();
        }

        return suppliers;
    }

}
