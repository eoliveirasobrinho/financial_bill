package com.itgen.financialit.domain.exception.supplier;

import com.itgen.financialit.domain.exception.BusinessException;

public class SupplierAlreadyExistsException extends BusinessException{

    public SupplierAlreadyExistsException(Long id) {
        super("Não foi possível criar o fornecedor ! Fornecedor já criado! ID: " + id);
    }

}
