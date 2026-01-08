package com.itgen.financialit.domain.exception.invoice;

import com.itgen.financialit.domain.exception.BusinessException;

public class InvoiceAlreadyCreatedException extends BusinessException{

    public InvoiceAlreadyCreatedException(String message) {
        super(message);
    }

}
