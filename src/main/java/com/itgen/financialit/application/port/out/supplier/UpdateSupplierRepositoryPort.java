package com.itgen.financialit.application.port.out.supplier;

import com.itgen.financialit.domain.model.Supplier;

public interface UpdateSupplierRepositoryPort {
    Supplier updateSupplier(Supplier supplier);
    Supplier findById(long id);
}
