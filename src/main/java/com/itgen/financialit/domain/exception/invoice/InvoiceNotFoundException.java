package com.itgen.financialit.domain.exception.invoice;

import com.itgen.financialit.domain.exception.BusinessException;

public class InvoiceNotFoundException extends BusinessException{

    public InvoiceNotFoundException(Long invoiceId) {
        super("Conta não encontrada para esse id: " + invoiceId);
    }

}
