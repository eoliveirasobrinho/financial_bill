package com.itgen.financialit.application.port.out;



import java.util.Optional;

import com.itgen.financialit.domain.model.Supplier;

public interface CreateSupplierRepositoryPort {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
}
