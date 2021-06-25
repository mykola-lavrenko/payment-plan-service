package com.mlavrenko.api.services;

import com.mlavrenko.api.domain.LoanDetails;
import com.mlavrenko.api.domain.PaymentDetails;
import com.mlavrenko.api.dto.PaymentPlanDTO;
import com.mlavrenko.api.services.calculators.PaymentsCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Payment plan service test")
@ExtendWith(MockitoExtension.class)
class PaymentPlanServiceTest {
    private PaymentPlanService paymentPlanService;
    @Mock
    private PaymentsCalculator calculator;
    @Mock
    private LoanDetails loanDetails;
    @Mock
    private List<PaymentDetails> paymentDetails;

    @BeforeEach
    void before() {
        paymentPlanService = new PaymentPlanService(calculator);
    }

    @Test
    @DisplayName("calculatePaymentPlan should return dto with expected list of payments")
    void calculatePaymentPlan() {
        Mockito.when(calculator.calculatePayments(loanDetails)).thenReturn(paymentDetails);
        var paymentPlan = paymentPlanService.calculatePaymentPlan(loanDetails);

        assertAll(
                () -> assertThat(paymentPlan).usingRecursiveComparison().isEqualTo(PaymentPlanDTO.create(paymentDetails)),
                () -> Mockito.verify(calculator).calculatePayments(Mockito.any(LoanDetails.class))
        );
    }
}