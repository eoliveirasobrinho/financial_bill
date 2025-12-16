package com.itgen.financialit.adapters.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itgen.financialit.adapters.in.rest.dto.RequestInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.ResponseInvoicePayableDTO;

@RestController
@RequestMapping("/invoice/payable")
public class InvoicePayableController {


    @PostMapping("/create")
    public ResponseEntity<RequestInvoicePayableDTO> createInvoiceToPay(@RequestBody RequestInvoicePayableDTO data) {
        return ResponseEntity.ok(data);
    }

    @GetMapping("/all-invoices")
    public ResponseEntity<ResponseInvoicePayableDTO> getInvoicesPayable(){
        return ResponseEntity.status(200).build();
    }
}
