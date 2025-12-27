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

import com.itgen.financialit.adapters.in.rest.dto.ResponseInvoicePayableDTO;
import com.itgen.financialit.adapters.in.rest.mapper.InvoicePayableMapper;
import com.itgen.financialit.application.service.CreateInvoicePayableService;
import com.itgen.financialit.application.service.PayInvoicePayableService;
import com.itgen.financialit.domain.model.InvoicePayable;

@WebMvcTest(InvoicePayableController.class)
@TestPropertySource(properties = {
        "server.servlet.context-path="
})
public class InvoicePayableControllerTest {
    
    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private CreateInvoicePayableService createInvoicePayableService;

    @MockBean
    private PayInvoicePayableService payInvoicePayableService;

  
    @MockBean
    private InvoicePayableMapper invoicePayableMapper;


    @Test
    void shouldCreateinvoice() throws Exception {
        when(invoicePayableMapper.toDomain(any()))
                .thenReturn(mock(InvoicePayable.class));

        when(createInvoicePayableService.createInvoicePayable(any()))
                .thenReturn(mock(InvoicePayable.class));

        when(invoicePayableMapper.toResponse(any()))
                .thenReturn(mock(ResponseInvoicePayableDTO.class));

        mockMvc.perform(post("/invoice/payable/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                  "description": "Conta teste",
                  "amount": 415,
                  "dueDate": "25-02-2026",
                  "category": "INTERNET",
                  "supplierId": 4
                }
                """))
                .andExpect(status().isCreated());
    }

        @Test
        void shouldReturnBadRequestWhenPayloadIsInvalid() throws Exception {
                mockMvc.perform(post("/invoice/payable/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                  "description": "Conta teste",
                  "dueDate": "25-02-2026",
                  "category": "internet",
                  "supplierId": 4
                 }
                 """))
                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnInternalServerErrorWhenUnexpectedErrorOccurs() throws Exception {
                when(invoicePayableMapper.toDomain(any()))
                .thenReturn(mock(InvoicePayable.class));

                when(createInvoicePayableService.createInvoicePayable(any()))
                .thenThrow(new RuntimeException("DB down"));

                when(invoicePayableMapper.toResponse(any()))
                .thenReturn(mock(ResponseInvoicePayableDTO.class));

                mockMvc.perform(post("/invoice/payable/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                                "description": "Conta teste",
                                "amount": 415,
                                "dueDate": "25-02-2026",
                                "category": "INTERNET",
                                "supplierId": 4
                        }
                        """))
                        .andExpect(status().isInternalServerError());
        }



}
