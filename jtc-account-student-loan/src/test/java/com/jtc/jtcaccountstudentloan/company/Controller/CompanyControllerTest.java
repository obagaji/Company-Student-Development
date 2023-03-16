package com.jtc.jtcaccountstudentloan.company.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import com.jtc.jtcaccountstudentloan.company.exceptionManager.CompanyNoNameException;
import com.jtc.jtcaccountstudentloan.company.service.CompanyService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(value = SpringExtension.class)
@WebMvcTest
class CompanyControllerTest {


    @MockBean
    CompanyService companyServiceMock;

    @Autowired
    MockMvc mockMvc;

    @Test
    void saveLoanRequest() throws Exception {
        CompanyLoan companyLoan = new CompanyLoan(100l,"musa Daniel",
                "mlsdanny@yahoo.com","musadaniel","admin");
        Mockito.when(companyServiceMock.saveCustomerLoanAmount(companyLoan)).thenReturn(companyLoan);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{staffId}", 100l)
                        .content(new ObjectMapper().writeValueAsString(companyLoan))
                                .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(result -> result.getResponse().setStatus(HttpServletResponse.SC_ACCEPTED))
                .andReturn();
    }

    @Test
    void getCompanyLoanItem() throws Exception
    {
        long loanId = 100l;
        CompanyLoan companyLoan = new CompanyLoan(100l,"musa Daniel",
                "mlsdanny@yahoo.com","musadaniel","admin");
        Mockito.when(companyServiceMock.getLoanId(loanId)).thenReturn(companyLoan);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/loan/{loanId}", 100L)
                .content(new ObjectMapper().writeValueAsString(companyLoan))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(result -> result.getResponse().setStatus(HttpServletResponse.SC_ACCEPTED))
                .andReturn();



    }
}