package com.mlavrenko.api.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class PaymentDetailsBuilder {
    private BigDecimal borrowerPaymentAmount;
    private ZonedDateTime date;
    private BigDecimal initialOutstandingPrincipal;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal remainingOutstandingPrincipal;

    private PaymentDetailsBuilder() {
    }

    public static PaymentDetailsBuilder create() {
        return new PaymentDetailsBuilder();
    }

    public PaymentDetailsBuilder setBorrowerPaymentAmount(BigDecimal borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        return this;
    }

    public PaymentDetailsBuilder setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public PaymentDetailsBuilder setInitialOutstandingPrincipal(BigDecimal initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        return this;
    }

    public PaymentDetailsBuilder setInterest(BigDecimal interest) {
        this.interest = interest;
        return this;
    }

    public PaymentDetailsBuilder setPrincipal(BigDecimal principal) {
        this.principal = principal;
        return this;
    }

    public PaymentDetailsBuilder setRemainingOutstandingPrincipal(BigDecimal remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
        return this;
    }

    public PaymentDetails createPeriodPaymentDetails() {
        return new PaymentDetails(
                borrowerPaymentAmount,
                date,
                initialOutstandingPrincipal,
                interest,
                principal,
                remainingOutstandingPrincipal);
    }
}