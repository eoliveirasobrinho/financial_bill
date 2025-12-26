package com.itgen.financialit.application.port.in;

import com.itgen.financialit.domain.model.Supplier;

public interface CreateSupplierUseCase {
    Supplier createSupplier(Supplier suplier);
}
