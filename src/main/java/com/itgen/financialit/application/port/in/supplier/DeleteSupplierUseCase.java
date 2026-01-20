package com.itgen.financialit.application.port.in.supplier;

import com.itgen.financialit.domain.model.Supplier;

public interface DeleteSupplierUseCase {
    void deleteSupplier(Long id);
    Supplier findById(Long id);
}
