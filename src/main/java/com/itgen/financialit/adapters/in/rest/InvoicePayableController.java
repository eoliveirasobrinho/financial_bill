package com.itgen.financialit.adapters.in.rest;


import java.time.LocalDate;

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
import com.itgen.financialit.adapters.in.rest.mapper.InvoicePayableMapper;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapper;
import com.itgen.financialit.adapters.out.persistence.mapper.InvoicePayablePersistenceMapperImpl;
import com.itgen.financialit.application.service.CreateInvoicePayableService;
import com.itgen.financialit.application.service.PayInvoicePayableService;
import com.itgen.financialit.domain.model.InvoicePayable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/invoice/payable")
public class InvoicePayableController {

    
    private final PayInvoicePayableService payInvoicePayableService;
    private final CreateInvoicePayableService createInvoicePayableService;
    private final InvoicePayableMapper mapper;

    public InvoicePayableController(
        PayInvoicePayableService payInvoicePayableService, 
        CreateInvoicePayableService createInvoicePayableService,
        InvoicePayableMapper mapper    
    ) {
        this.payInvoicePayableService = payInvoicePayableService;
        this.createInvoicePayableService = createInvoicePayableService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseInvoicePayableDTO> createInvoiceToPay(@RequestBody RequestInvoicePayableDTO requestDto) {
        System.out.println(requestDto);
        InvoicePayable invoice = mapper.toDomain(requestDto);
        System.out.println(invoice);
        InvoicePayable invoiceCreated = createInvoicePayableService.createInvoicePayable(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(invoiceCreated));
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
    @ResponseStatus(HttpStatus.OK)
    public void pay(@PathVariable Long id, LocalDate paymenDate) {
        payInvoicePayableService.pay(id, paymenDate);
    }

    
    
    
}
