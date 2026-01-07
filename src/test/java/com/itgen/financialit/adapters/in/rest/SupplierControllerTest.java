package com.itgen.financialit.adapters.in.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.itgen.financialit.adapters.in.rest.dto.response.ResponseSupplierDTO;
import com.itgen.financialit.adapters.in.rest.mapper.SupplierMapper;
import com.itgen.financialit.application.service.CreateSupplierService;
import com.itgen.financialit.domain.model.Supplier;

@WebMvcTest(SupplierController.class)
@TestPropertySource(properties = {
    "server.servlet.context-path="
})
public class SupplierControllerTest{

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private CreateSupplierService supplierService;

    @MockBean
    private SupplierMapper supplierMapper;

    @Test
    void shouldCreateSupplier() throws Exception{
        when(supplierMapper.toDomain(any())).thenReturn(mock(Supplier.class));

        when(supplierService.createSupplier(any())).thenReturn(mock(Supplier.class));

        when(supplierMapper.toResponse(any())).thenReturn(mock(ResponseSupplierDTO.class));

        mockMvc.perform(post("/supplier/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                    "name": "vivo",
                    "document": "12312121312",
                    "email": "teste4@gmail.com"
                }
                """))
                .andExpect(status().isCreated());


    }

}