package com.mlavrenko.api.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class PaymentDetails {
    private final BigDecimal borrowerPaymentAmount;

    private final ZonedDateTime date;
    private final BigDecimal initialOutstandingPrincipal;
    private final BigDecimal interest;
    private final BigDecimal principal;
    private final BigDecimal remainingOutstandingPrincipal;
    public PaymentDetails(BigDecimal borrowerPaymentAmount, ZonedDateTime date,
                          BigDecimal initialOutstandingPrincipal, BigDecimal interest,
                          BigDecimal principal, BigDecimal remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public BigDecimal getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "borrowerPaymentAmount=" + borrowerPaymentAmount +
                ", date=" + date +
                ", initialOutstandingPrincipal=" + initialOutstandingPrincipal +
                ", interest=" + interest +
                ", principal=" + principal +
                ", remainingOutstandingPrincipal=" + remainingOutstandingPrincipal +
                '}';
    }
}

