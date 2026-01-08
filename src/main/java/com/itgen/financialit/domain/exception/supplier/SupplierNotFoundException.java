package com.itgen.financialit.domain.exception.supplier;

import com.itgen.financialit.domain.exception.BusinessException;

public class SupplierNotFoundException extends BusinessException{

    protected SupplierNotFoundException(Long id) {
        super("Fornecedor não encontrado para este ID: " + id);
    }

}
