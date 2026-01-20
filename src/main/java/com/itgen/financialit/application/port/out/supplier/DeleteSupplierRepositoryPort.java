package com.itgen.financialit.application.port.out.supplier;

import java.util.Optional;

import com.itgen.financialit.domain.model.Supplier;

public interface DeleteSupplierRepositoryPort {
    void deleteSupplier(Long id);
    Supplier findById(Long id);
}
