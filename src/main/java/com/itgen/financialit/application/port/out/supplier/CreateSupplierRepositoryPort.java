package com.itgen.financialit.application.port.out.supplier;



import java.util.Optional;

import com.itgen.financialit.domain.model.Supplier;

public interface CreateSupplierRepositoryPort {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
}
