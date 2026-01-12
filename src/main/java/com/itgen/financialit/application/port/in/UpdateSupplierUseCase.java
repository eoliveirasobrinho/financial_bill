package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.Supplier;

public interface UpdateSupplierUseCase {
    Supplier updateSupplier(Supplier supplier);
    Supplier findById(Long id);
}
