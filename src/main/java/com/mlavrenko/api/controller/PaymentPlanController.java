package com.mlavrenko.api.controller;

import com.mlavrenko.api.domain.LoanDetails;
import com.mlavrenko.api.dto.PaymentPlanDTO;
import com.mlavrenko.api.services.PaymentPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PaymentPlanController {
    private final PaymentPlanService paymentPlanService;

    public PaymentPlanController(PaymentPlanService paymentPlanService) {
        this.paymentPlanService = paymentPlanService;
    }

    @PostMapping(value = "generate-plan", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentPlanDTO calculatePaymentPlan(@Valid @RequestBody LoanDetails loanDetails) {
        return paymentPlanService.calculatePaymentPlan(loanDetails);
    }
}
