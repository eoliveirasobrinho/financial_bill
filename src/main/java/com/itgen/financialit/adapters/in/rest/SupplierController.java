package com.itgen.financialit.adapters.in.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itgen.financialit.adapters.in.rest.dto.request.RequestSuplierDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseSupplierDTO;
import com.itgen.financialit.adapters.in.rest.mapper.SupplierMapper;
import com.itgen.financialit.application.service.CreateSupplierService;
import com.itgen.financialit.domain.model.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final CreateSupplierService service;
    private final SupplierMapper mapper;

    public SupplierController(CreateSupplierService service, SupplierMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseSupplierDTO> createSupplier(@RequestBody RequestSuplierDTO entity) {
        Supplier supplier = mapper.toDomain(entity);
        Supplier supplierToMapper = service.createSupplier(supplier);
        ResponseSupplierDTO supplierCreated = mapper.toResponse(supplierToMapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierCreated);
    }
    
}
