package com.itgen.financialit.application.port.in.supplier;

import com.itgen.financialit.domain.model.Supplier;

public interface GetSupplierByIdUseCase {
    Supplier findById(Long id);
}
