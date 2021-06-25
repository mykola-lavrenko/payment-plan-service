package com.mlavrenko.api.dto;

import com.mlavrenko.api.domain.PaymentDetails;

import java.util.List;

public class PaymentPlanDTO {
    private final List<PaymentDetails> borrowerPayments;

    private PaymentPlanDTO(List<PaymentDetails> borrowerPayments) {
        this.borrowerPayments = borrowerPayments;
    }

    public static PaymentPlanDTO create(List<PaymentDetails> borrowerPayments) {
        return new PaymentPlanDTO(borrowerPayments);
    }


    public List<PaymentDetails> getBorrowerPayments() {
        return borrowerPayments;
    }
}
