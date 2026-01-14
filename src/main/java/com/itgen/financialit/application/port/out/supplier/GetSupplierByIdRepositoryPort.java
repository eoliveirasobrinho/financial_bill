package com.itgen.financialit.application.port.out.supplier;

import com.itgen.financialit.domain.model.Supplier;

public interface GetSupplierByIdRepositoryPort {
    Supplier findById(Long id);
}
