package com.itgen.financialit.adapters.in.rest;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itgen.financialit.adapters.in.rest.dto.request.RequestInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.mapper.InvoicePayableMapper;
import com.itgen.financialit.application.service.invoice.CreateInvoicePayableService;
import com.itgen.financialit.application.service.invoice.DeleteInvoicePayableService;
import com.itgen.financialit.application.service.invoice.FindByIdInvoicePayableService;
import com.itgen.financialit.application.service.invoice.GetAllInvoicesPayablesService;
import com.itgen.financialit.application.service.invoice.UpdateInvoicePayableService;
import com.itgen.financialit.domain.model.InvoicePayable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/invoice/payable")
public class InvoicePayableController {

    
    
    private final CreateInvoicePayableService createInvoicePayableService;
    private final GetAllInvoicesPayablesService getAllInvoicesPayableService;
    private final FindByIdInvoicePayableService findByIdInvoicePayableService;
    private final DeleteInvoicePayableService deleteInvoicePayableService;
    private final UpdateInvoicePayableService updateInvoicePayableService;
    private final InvoicePayableMapper mapper;

    public InvoicePayableController( 
        CreateInvoicePayableService createInvoicePayableService,
        GetAllInvoicesPayablesService getAllInvoicesPayableService,
        FindByIdInvoicePayableService findByIdInvoicePayableService,
        DeleteInvoicePayableService deleteInvoicePayableService,
        UpdateInvoicePayableService updateInvoicePayableService,
        InvoicePayableMapper mapper
    ) {
        
        this.createInvoicePayableService = createInvoicePayableService;
        this.getAllInvoicesPayableService = getAllInvoicesPayableService;
        this.findByIdInvoicePayableService = findByIdInvoicePayableService;
        this.deleteInvoicePayableService = deleteInvoicePayableService;
        this.updateInvoicePayableService = updateInvoicePayableService;
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
        InvoicePayable invoice = findByIdInvoicePayableService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(invoice));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseInvoicePayableDTO> updateInvoicePayable(@PathVariable Long id, @RequestBody RequestInvoicePayableDTO requestDto) {
        InvoicePayable invoice = mapper.toDomain(requestDto);
        InvoicePayable result = updateInvoicePayableService.updateInvoicePayable(invoice);
        
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(result));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceById(@PathVariable Long id) {
       deleteInvoicePayableService.deleteInvoicePayable(id);
    }
   
    
    
}
