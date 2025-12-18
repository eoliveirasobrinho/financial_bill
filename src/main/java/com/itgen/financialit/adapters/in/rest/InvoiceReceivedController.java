package com.itgen.financialit.adapters.in.rest;

import org.springframework.web.bind.annotation.RestController;

import com.itgen.financialit.adapters.in.rest.dto.RequestInvoiceReceivedDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping("/invoice/received")
public class InvoiceReceivedController {

    @GetMapping("/all-invoices")
    public ResponseEntity<RequestInvoiceReceivedDTO> getAllReceivedInvoices() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/invoice/{id}")
    public ResponseEntity<RequestInvoiceReceivedDTO> createReceivedInvoice(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("upodate-invoice/{id}")
    public ResponseEntity<RequestInvoiceReceivedDTO> updateReceivedInvoice(@PathVariable Long id, @RequestBody RequestInvoiceReceivedDTO data) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete-invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReceivedInvoice(@PathVariable Long id) {
        
    }
    
    
}
