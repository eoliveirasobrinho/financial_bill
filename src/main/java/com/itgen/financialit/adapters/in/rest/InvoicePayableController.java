package com.itgen.financialit.adapters.in.rest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import com.itgen.financialit.application.service.CreateInvoicePayableService;
import com.itgen.financialit.application.service.FindByIdInvoicePayableService;
import com.itgen.financialit.application.service.GetAllInvoicesPayablesService;
import com.itgen.financialit.domain.model.InvoicePayable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/invoice/payable")
public class InvoicePayableController {

    
    
    private final CreateInvoicePayableService createInvoicePayableService;
    private final GetAllInvoicesPayablesService getAllInvoicesPayableService;
    private final FindByIdInvoicePayableService findByIdInvoicePayableService;
    private final InvoicePayableMapper mapper;

    public InvoicePayableController( 
        CreateInvoicePayableService createInvoicePayableService,
        GetAllInvoicesPayablesService getAllInvoicesPayableService,
        FindByIdInvoicePayableService findByIdInvoicePayableService,
        InvoicePayableMapper mapper
    ) {
        
        this.createInvoicePayableService = createInvoicePayableService;
        this.getAllInvoicesPayableService = getAllInvoicesPayableService;
        this.findByIdInvoicePayableService = findByIdInvoicePayableService;
        this.mapper = mapper;
        
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseInvoicePayableDTO> createInvoiceToPay(@RequestBody RequestInvoicePayableDTO requestDto) {
        InvoicePayable invoice = mapper.toDomain(requestDto); 
        InvoicePayable invoiceCreated = createInvoicePayableService.createInvoicePayable(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(invoiceCreated));
    }

    @GetMapping("/all-invoices")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResponseInvoicePayableDTO>> getInvoicesPayable(){
        List<InvoicePayable> invoices = getAllInvoicesPayableService.getAllInvoicesPayable();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseList(invoices));
        
    }

    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseInvoicePayableDTO> findById(@PathVariable long id) {
        Optional<InvoicePayable> invoice = findByIdInvoicePayableService.findById(id);
        InvoicePayable invoiceToReturn = invoice.get();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(invoiceToReturn));
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
       
    }

    
    
    
}
