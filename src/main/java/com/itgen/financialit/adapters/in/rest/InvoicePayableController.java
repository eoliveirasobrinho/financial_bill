package com.itgen.financialit.adapters.in.rest;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itgen.financialit.adapters.in.rest.dto.RequestInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.ResponseInvoicePayableDTO;
import com.itgen.financialit.application.port.in.PayInvoicePayableUseCase;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/invoice/payable")
public class InvoicePayableController {

    
    private final PayInvoicePayableUseCase payInvoicePayableUseCase;

    public InvoicePayableController(PayInvoicePayableUseCase payInvoicePayableUseCase) {
        this.payInvoicePayableUseCase = payInvoicePayableUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<RequestInvoicePayableDTO> createInvoiceToPay(@RequestBody RequestInvoicePayableDTO requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all-invoices")
    public ResponseEntity<ResponseInvoicePayableDTO> getInvoicesPayable(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseInvoicePayableDTO> updateInvoicePayable(@PathVariable Long id, @RequestBody RequestInvoicePayableDTO requestDto) {
        //TODO: process PUT request
        
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceById(@PathVariable Long id) {
       // TODO: process delete request
    }

    @PatchMapping("/pay/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pay(@PathVariable Long id, LocalDate paymenDate) {
        payInvoicePayableUseCase.pay(id, paymenDate);
    }

    
    
    
}
