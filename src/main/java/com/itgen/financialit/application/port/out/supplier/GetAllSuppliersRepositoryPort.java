package com.itgen.financialit.application.port.out.supplier;

import java.util.List;

import com.itgen.financialit.domain.model.Supplier;

public interface GetAllSuppliersRepositoryPort {
    List<Supplier> getAllSuppliers();
}
