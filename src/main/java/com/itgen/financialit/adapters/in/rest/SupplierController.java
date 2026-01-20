package com.itgen.financialit.adapters.in.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itgen.financialit.adapters.in.rest.dto.request.RequestSuplierDTO;
import com.itgen.financialit.adapters.in.rest.dto.response.ResponseSupplierDTO;
import com.itgen.financialit.adapters.in.rest.mapper.SupplierMapper;
import com.itgen.financialit.application.service.supplier.CreateSupplierService;
import com.itgen.financialit.application.service.supplier.DeleteSupplierService;
import com.itgen.financialit.application.service.supplier.GetAllSuppliersService;
import com.itgen.financialit.application.service.supplier.GetSupplierByIdService;
import com.itgen.financialit.application.service.supplier.UpdateSupplierService;
import com.itgen.financialit.domain.model.Supplier;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final CreateSupplierService createService;
    private final GetAllSuppliersService getAllSuppliersService;
    private final UpdateSupplierService updateSupplierService;
    private final GetSupplierByIdService getSupplierByIdService;
    private final DeleteSupplierService deleteSupplierService;
    private final SupplierMapper mapper;

    public SupplierController(
        CreateSupplierService createService, 
        SupplierMapper mapper, 
        GetAllSuppliersService getAllSuppliersService, 
        UpdateSupplierService updateSupplierService, 
        GetSupplierByIdService getSupplierByIdService,
        DeleteSupplierService deleteSupplierService) {
            
        this.mapper = mapper;
        this.createService = createService;
        this.getAllSuppliersService = getAllSuppliersService;
        this.updateSupplierService = updateSupplierService;
        this.getSupplierByIdService = getSupplierByIdService;
        this.deleteSupplierService = deleteSupplierService;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseSupplierDTO> createSupplier(@RequestBody RequestSuplierDTO requestSupplierDTO) {
        Supplier supplier = mapper.toDomain(requestSupplierDTO);
        Supplier supplierToMapper = createService.createSupplier(supplier);
        ResponseSupplierDTO supplierCreated = mapper.toResponse(supplierToMapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierCreated);
    }

    @GetMapping("/suppliers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResponseSupplierDTO>> getAllSuppliers(){
        List<Supplier> list = getAllSuppliersService.getAllSuppliers();
        List<ResponseSupplierDTO> suppliers = mapper.toResponseList(list);
        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
       
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseSupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody RequestSuplierDTO dto) {
        Supplier result = mapper.toDomain(dto);
        Supplier supplier = updateSupplierService.updateSupplier(result);
        ResponseSupplierDTO response = mapper.toResponse(supplier);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSupplierDTO> getSupplierById(@PathVariable Long id) {
        Supplier result = getSupplierByIdService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(result));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSupplier(@PathVariable Long id) {
        deleteSupplierService.deleteSupplier(id);
    }
    
}
