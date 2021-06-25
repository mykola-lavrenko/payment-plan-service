package com.mlavrenko.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlavrenko.api.domain.LoanDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Payment plan controller test")
@SpringBootTest
@AutoConfigureMockMvc
class PaymentPlanControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("create should create new entity and return status 201")
    void calculatePaymentPlan() throws Exception {
        String date = "2018-01-01T00:00:00Z";
        var loanDetails = setUpLoanDetails(date);
        String value = objectMapper.writeValueAsString(loanDetails);

        mockMvc.perform(post("/generate-plan")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(value)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.borrowerPayments", hasSize(loanDetails.getDuration())))
                .andExpect(jsonPath("$.borrowerPayments.[0].borrowerPaymentAmount", equalTo(1004.17)))
                .andExpect(jsonPath("$.borrowerPayments.[0].initialOutstandingPrincipal", equalTo(1000)))
                .andExpect(jsonPath("$.borrowerPayments.[0].date", equalTo(date)))
                .andExpect(jsonPath("$.borrowerPayments.[0].interest", equalTo(4.17)))
                .andExpect(jsonPath("$.borrowerPayments.[0].principal", equalTo(1000)))
                .andExpect(jsonPath("$.borrowerPayments.[0].remainingOutstandingPrincipal", equalTo(0)));
    }

    private LoanDetails setUpLoanDetails(String date) {
        var validLoanDetails = new LoanDetails();
        validLoanDetails.setDuration(1);
        validLoanDetails.setNominalRate(BigDecimal.valueOf(5));
        validLoanDetails.setLoanAmount(BigDecimal.valueOf(1000));
        validLoanDetails.setStartDate(ZonedDateTime.parse(date));
        return validLoanDetails;
    }

    @Test
    @DisplayName("create should return status 400 when loan detains has invalid properties")
    void calculatePaymentPlanBadRequest() throws Exception {
        var loanDetails = new LoanDetails();
        String value = objectMapper.writeValueAsString(loanDetails);

        mockMvc.perform(post("/generate-plan")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(value)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}