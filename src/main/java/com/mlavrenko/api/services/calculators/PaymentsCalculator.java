package com.mlavrenko.api.services.calculators;

import com.mlavrenko.api.domain.LoanDetails;
import com.mlavrenko.api.domain.PaymentDetails;

import java.util.List;

public interface PaymentsCalculator {
    List<PaymentDetails> calculatePayments(LoanDetails loanDetails);
}
