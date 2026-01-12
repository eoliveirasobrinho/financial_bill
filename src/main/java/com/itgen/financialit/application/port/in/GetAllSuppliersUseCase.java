package com.itgen.financialit.application.port.in;

import java.util.List;

import com.itgen.financialit.domain.model.Supplier;

public interface GetAllSuppliersUseCase {
    List<Supplier> getAllSuppliers();
}
