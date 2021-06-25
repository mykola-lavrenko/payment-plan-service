package com.mlavrenko.api.services;

import com.mlavrenko.api.domain.LoanDetails;
import com.mlavrenko.api.dto.PaymentPlanDTO;
import com.mlavrenko.api.services.calculators.PaymentsCalculator;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class PaymentPlanService {
    private final PaymentsCalculator calculator;

    public PaymentPlanService(PaymentsCalculator calculator) {
        this.calculator = calculator;
    }

    public PaymentPlanDTO calculatePaymentPlan(@NotNull LoanDetails loanDetails) {
        var borrowerPayments = calculator.calculatePayments(loanDetails);
        return PaymentPlanDTO.create(borrowerPayments);
    }
}
